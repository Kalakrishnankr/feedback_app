package com.example.raghulsn.feedback_new;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raghulsn.feedback_new.model.DBHelper;
import com.example.raghulsn.feedback_new.model.FactoryClass;
import com.example.raghulsn.feedback_new.model.FeedbackApp;
import com.example.raghulsn.feedback_new.model.Question;
import com.example.raghulsn.feedback_new.model.Questionlist;
import com.example.raghulsn.feedback_new.model.Questions;
import com.example.raghulsn.feedback_new.model.Ratinglist;
import com.example.raghulsn.feedback_new.model.Session;
import com.example.raghulsn.feedback_new.model.Sessionmanager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static com.example.raghulsn.feedback_new.model.Sessionmanager.KEY_NAME;
import static com.example.raghulsn.feedback_new.model.Sessionmanager.KEY_PASS;
import static com.example.raghulsn.feedback_new.model.Sessionmanager.PREF_NAME;

/**
 * Created by kalakrishnan.kr on 12/10/16.
 */
public class Settings extends Activity  {
    Button next;
    Button reset;
    ImageView l;
    ImageView logout;
    TextView add;
    RecyclerView lview1;
    DBHelper db;
    Cursor resultSet;
    int rtcount;
    RecyclerAdapter adapter;
    Integer qid,count;
    String rt;
    FeedbackApp app;
    Sessionmanager session;
    SharedPreferences shared;
    String uname;
    String pass;
    FactoryClass mFactory;
    String api,apirate;
    FactoryClass.ResponseMessage responseMessage,responseMessagerate;
    Handler handler;
    private  final int SUCCESS = 200;
    private  int SUCCESSRATE ;
    private static final int API_FAIL = 401 ;


    //RecyclerView recyclerView;
    List<Ratinglist> RatingLIst;
    List<Questionlist> questionList = new ArrayList<Questionlist>();
    List<Question> question = new ArrayList<Question>();

    AnimatedRatingBar aRB1,aRB2,aRB3,aRB4;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        l=(ImageView)findViewById(R.id.lfarw);
        logout=(ImageView)findViewById(R.id.logout);
        reset=(Button) findViewById(R.id.reset);
        add=(TextView)findViewById(R.id.txt3);
        session = new Sessionmanager(getApplicationContext());
        mFactory=new FactoryClass(getApplicationContext());
        lview1 = (RecyclerView) findViewById(R.id.lview);
        lview1.setLayoutManager(new LinearLayoutManager(this));
        shared = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        uname = (shared.getString(KEY_NAME, ""));
        pass= (shared.getString(KEY_PASS, ""));
        db = new DBHelper(this);
        app = (FeedbackApp) getApplicationContext();
        api="getAllQuestions/";
        apirate="getOverAllRatings/";
        RatingLIst = new ArrayList<Ratinglist>();
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what)
                {
                    case SUCCESS:
                        /*Gson googleJson = new Gson();
                        Questions jsonObjList = googleJson.fromJson(String.valueOf(responseMessage.message), Questions.class);*/

                        try {
                            Gson gson = new Gson();
                            Questions apiResponse = gson.fromJson(responseMessage.message, Questions.class);
                            if (apiResponse.Result != null) {
                                questionList = apiResponse.Result;
                                //question = apiResponse.Result1;
                                rate();

                             /*   if(questionList != null || questionList.size() > 0)
                                {
                                    adapter=new RecyclerAdapter();
                                    lview1.setAdapter(adapter);
                                }*/

                            }
//                            List<Questionlist> questionList = Arrays.asList(gson.fromJson(responseMessage.message, Questionlist.class));
//                            if(questionList != null)
//                            {
//                                Toast.makeText(getApplicationContext(), responseMessage.message,Toast.LENGTH_SHORT).show();
//                            }
//                            Toast.makeText(getApplicationContext(), ,Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        break;

                    case 10:
                        /*Gson googleJson = new Gson();
                        Questions jsonObjList = googleJson.fromJson(String.valueOf(responseMessage.message), Questions.class);*/

                        try {
                            Gson gson = new Gson();
                            Questions apiResponse = gson.fromJson(responseMessage.message, Questions.class);
                            if (apiResponse.ResultRate != null) {
                                RatingLIst = apiResponse.ResultRate;

                                    adapter=new RecyclerAdapter();
                                    lview1.setAdapter(adapter);


                                //question = apiResponse.Result1;

                               /* if(questionList != null || questionList.size() > 0)
                                {
                                    adapter=new RecyclerAdapter();
                                    lview1.setAdapter(adapter);
                                }*/

                            }

//                            List<Questionlist> questionList = Arrays.asList(gson.fromJson(responseMessage.message, Questionlist.class));
//                            if(questionList != null)
//                            {
//                                Toast.makeText(getApplicationContext(), responseMessage.message,Toast.LENGTH_SHORT).show();
//                            }
//                            Toast.makeText(getApplicationContext(), ,Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        break;

                    case API_FAIL:

                         Toast.makeText(getApplicationContext(),"API Failure...",Toast.LENGTH_SHORT).show();
                        break;
                    default:

                        Toast.makeText(getApplicationContext(),"API Failure...",Toast.LENGTH_SHORT).show();
                        break;

                }
                return false;
            }
        });
//        resultSet = db.getRatingList();
        rtcount=db.getRating();

        //resultSet = db.getQuestnDetails();
        //resultSet = (Cursor) mFactory.executeRequest(api,uname,pass);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    responseMessage = mFactory.executeRequest(api,uname,pass);
                    if(responseMessage != null)
                    {
                        switch (responseMessage.responseCode)
                        {
                            case 200:
                            case 201:
                                handler.sendEmptyMessage(SUCCESS);
                                break;
                            default:
                                handler.sendEmptyMessage(API_FAIL);
                                break;

                        }

                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });t.start();




       // RatingLIst= new ArrayList<Ratelist>();
        /*try {

            JSONArray jArray = new JSONArray(responseMessage);

            if (jArray != null) {

                resultSet.moveToFirst();
                do {

                    questionList.add(new Questionlist(resultSet.getInt(0),resultSet.getString(1)));
//                    RatingLIst.add(new Ratelist( resultSet.getInt(0),resultSet.getString(1), resultSet.getInt(2)));
                } while (resultSet.moveToNext());
            }
        }

        catch (Exception e){
            Toast.makeText(this, "Nothing to display", Toast.LENGTH_SHORT).show();

        }*/

            /*if (resultSet != null) {

                resultSet.moveToFirst();
                do {

                    questionList.add(new Questionlist(resultSet.getInt(0),resultSet.getString(1)));
//                    RatingLIst.add(new Ratelist( resultSet.getInt(0),resultSet.getString(1), resultSet.getInt(2)));
                } while (resultSet.moveToNext());
            }
        }

        catch (Exception e){
            Toast.makeText(this, "Nothing to display", Toast.LENGTH_SHORT).show();

        }*/

       /* if(questionList != null || questionList.size() > 0)
        {
            adapter=new RecyclerAdapter();
            lview1.setAdapter(adapter);
        }*/





//        next= (Button) findViewById(R.id.imgV1_right);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Settings.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                //overridePendingTransition(R.layout.bounce1, R.layout.bounce1);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
            }
        });

       logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //app.session = new Session(this);
                session.logoutUser();
                Intent intent=new Intent(Settings.this,Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                //overridePendingTransition(R.layout.bounce1, R.layout.bounce1);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
            }
        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //reset.setBackgroundResource(R.drawable.buttons01);
                Intent intent=new Intent(Settings.this,ResetPassword.class);
                startActivity(intent);
                overridePendingTransition(R.layout.slide_in_left, R.layout.slide_out_right);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Settings.this,Addquestion.class);
                startActivity(intent);
                overridePendingTransition(R.layout.slide_in_left, R.layout.slide_out_right);
            }
        });

    }


    protected void rate() {



        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    responseMessage = mFactory.executeRequest(apirate,uname,pass);
                    if(responseMessage != null)
                    {
                        switch (responseMessage.responseCode)
                        {
                            case 200:
                            case 201:
                                handler.sendEmptyMessage(10);
                                break;
                            default:
                                handler.sendEmptyMessage(API_FAIL);
                                break;

                        }

                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });t1.start();



        // disply();
    }

    public class RecyclerAdapter extends RecyclerView.Adapter<CustomViewHolder>
    {

       /* boolean status1;
        RecyclerAdapter(boolean status)
        {
           status1=status;


        }*/

        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rating_list, null);

            CustomViewHolder viewHolder = new CustomViewHolder(view);


            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final CustomViewHolder holder, final int position) {

            int cstmr = 0;
            int total = 0;

            int avg = 0;


            if(position==0) {
                qid = questionList.get(position).columnId;
                cstmr = db.getTotalcustmr(qid);
                holder.rlChild.setVisibility(View.VISIBLE);
                holder.rlChildtwo.setVisibility(View.VISIBLE);

                holder.cust.setText("Total Rating");
                holder.total.setText("" +cstmr);

                //qid = questionList.get(position).columnId;
                count=RatingLIst.get(position).totalrating;


                /*total = db.getTotalRating(qid);
                cstmr = db.getTotalcustmr(qid);
                if(rtcount==0){
                    avg=0;
                }
                else {
                    avg = (total / cstmr);
                }
*/
                holder.qs.setText(questionList.get(position).questn);

                holder.ratingBar.ratingBarNo = position;

                holder.ratingBar.setRatingsTo(count, null);
            }

            else {
                holder.rlChild.setVisibility(View.GONE);


                //qid = questionList.get(position).columnId;
                count=RatingLIst.get(position).totalrating;
                /*total = db.getTotalRating(qid);
                cstmr = db.getTotalcustmr(qid);
                if(rtcount==0){
                    avg=0;
                }
                else {
                    avg = (total / cstmr);
                }*/


                holder.qs.setText(questionList.get(position).questn);

                holder.ratingBar.ratingBarNo = position;
                holder.ratingBar.setRatingsTo(count, null);


           }
        }

        @Override
        public int getItemCount() {
            int rtnSize = 0;
            if(questionList != null)
                rtnSize  =  questionList.size();
            return rtnSize;
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

       public TextView qs,total,cust;
        public   AnimatedRatingBar ratingBar;

        public RelativeLayout rlMain,rlChild,rlChildtwo;
        public CustomViewHolder(View view) {
            super(view);
            /*textname = (TextView) view.findViewById(R.id.name);
            textage = (TextView) view.findViewById(R.id.age);
            textsex = (TextView) view.findViewById(R.id.sex);
            textid = (TextView) view.findViewById(R.id.no);*/
            qs = (TextView)view.findViewById(R.id.qs1);
            cust = (TextView)view.findViewById(R.id.qs2);
            total = (TextView)view.findViewById(R.id.cstmr);

            rlChild=(RelativeLayout)view.findViewById(R.id.rl6);
            rlChildtwo=(RelativeLayout)view.findViewById(R.id.rl7);

            //average = (TextView)view.findViewById(R.id.avg);
            ratingBar = (AnimatedRatingBar)view.findViewById(R.id.rb1);
            rlMain=(RelativeLayout)view.findViewById(R.id.rl1);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        FeedbackApp.isSettingsScreen=true;

        Animation pulse = AnimationUtils.loadAnimation(Settings.this, R.anim.pulse);
        l.startAnimation(pulse);
        app.session = new Session(this);
    }
}