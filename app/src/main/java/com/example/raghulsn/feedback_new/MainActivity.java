package com.example.raghulsn.feedback_new;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raghulsn.feedback_new.model.DBHelper;
import com.example.raghulsn.feedback_new.model.FactoryClass;
import com.example.raghulsn.feedback_new.model.FeedbackApp;
import com.example.raghulsn.feedback_new.model.Qustnrating;
import com.example.raghulsn.feedback_new.model.Session;
import com.example.raghulsn.feedback_new.model.Sessionmanager;
import com.google.gson.Gson;

import static com.example.raghulsn.feedback_new.model.Sessionmanager.KEY_NAME;
import static com.example.raghulsn.feedback_new.model.Sessionmanager.KEY_PASS;
import static com.example.raghulsn.feedback_new.model.Sessionmanager.PREF_NAME;

public class MainActivity extends Activity implements AnimatedRatingBar.OnRatingsChangedListner{

    Button next;
    //String MY_PREFS_NAME,value;
    int rt,strrating;
    ImageView r;
    ImageView set;
    ImageView imageView;
    AnimatedRatingBar aRB1;
    FeedbackApp app;
    DBHelper db;
    Sessionmanager getsession;
    String password ="";
    EditText edtPassword ;
    String pin;
    SharedPreferences shared;
    String uname;
    String pass;
    String api;
    FactoryClass.ResponseMessage responseMessage;
    Handler handler;
    FactoryClass mFactory;
    private  final int SUCCESS = 200;
    private static final int API_FAIL = 401 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        r=(ImageView)findViewById(R.id.right);
        shared = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        uname = (shared.getString(KEY_NAME, ""));
        pass= (shared.getString(KEY_PASS, ""));
        mFactory= new FactoryClass(getApplicationContext());
        api= "getAllQuestions/";
        aRB1 = (AnimatedRatingBar) findViewById(R.id.rlinner_img1);
        aRB1.ratingBarNo =0;

        aRB1.onRatingsChangedListner = this;

        app = (FeedbackApp) getApplicationContext();
        aRB1.onRatingsChangedListner = this;
        set=(ImageView)findViewById(R.id.imgV1_set);
        imageView=(ImageView)findViewById(R.id.imageView);

        Animation rotate = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate);
        set.startAnimation(rotate);
        getsession = new Sessionmanager(getApplicationContext());
       // session.checkLogin();

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what)
                {
                    case SUCCESS:

                        try {
                            Gson gson = new Gson();
                            Qustnrating apiResponse = gson.fromJson(responseMessage.message, Qustnrating.class);
                            if (apiResponse.Result != null) {
                                app.session.ratingsList = apiResponse.Result;


                            }


//
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //Toast.makeText(getApplicationContext(), responseMessage.message,Toast.LENGTH_SHORT).show();
                        break;

                    case API_FAIL:

                        // Toast.makeText(getApplicationContext(),"API Failure...",Toast.LENGTH_SHORT).show();
                        break;
                    default:

                        //Toast.makeText(getApplicationContext(),"API Failure...",Toast.LENGTH_SHORT).show();
                        break;

                }
                return false;
            }
        });



        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (strrating>0) {

                    Intent intent = new Intent(MainActivity.this, Gender.class);
                    startActivity(intent);
                    overridePendingTransition(R.layout.slide_in_right, R.layout.slide_out_left);
                }

                else
                {
                    Toast.makeText(MainActivity.this, "Please rate it", Toast.LENGTH_SHORT).show();
                }

            }
        });





       /* set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,Settings.class);
                startActivity(intent);
                //overridePendingTransition(R.layout.bounce, R.layout.bounce);
                overridePendingTransition(R.anim.push_down_in,R.anim.push_down_out);
                //loadAlertDialogBox();

            }
        });*/


        imageView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {

                //Settings page
                Intent intent=new Intent(MainActivity.this,Settings.class);
                startActivity(intent);
                //overridePendingTransition(R.layout.bounce, R.layout.bounce);
                overridePendingTransition(R.anim.push_down_in,R.anim.push_down_out);
                return true;
            }
        });



    }

    private void loadAlertDialogBox() {

        // Create custom dialog object
        final Dialog dialog = new Dialog(this);
        // Include dialog.xml file
        dialog.setContentView(R.layout.customdialog);
        // Set dialog title
        dialog.setTitle("Pin Number");

        // set values for custom dialog components - text, image and button
         edtPassword = (EditText) dialog.findViewById(R.id.edtText);
        Button btnOk = (Button) dialog.findViewById(R.id.btnok);
        Button btnCancel = (Button) dialog.findViewById(R.id.btncancel);
        dialog.show();
        // if decline button is clicked, close the custom dialog
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                dialog.dismiss();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 pin = edtPassword.getText().toString();
                if(pin.equals("1234")) {
                    Intent intent = new Intent(MainActivity.this, Settings.class);
                    startActivity(intent);
                    //overridePendingTransition(R.layout.bounce, R.layout.bounce);
                    overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
                }
                dialog.dismiss();
            }
        });
    }




    @Override
    protected void onResume() {
        super.onResume();
        getquestion();
        FeedbackApp.isSettingsScreen=false;
        if(app.session.ratingsList != null &&  app.session.ratingsList.size() > 0 ) {
            strrating = app.session.ratingsList.get(0).getRate();
            aRB1.setRatingsTo(app.session.ratingsList.get(0).getRate(), null);

        }
        else {
            app.session = new Session(this);

        }
    }

    public void getquestion() {



        Thread t1 = new Thread(new Runnable() {
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
        });t1.start();


        // disply();
    }



   /* @Override
    protected void onResume() {
        super.onResume();
        FeedbackApp.isSettingsScreen=false;
        strrating=app.session.ratingsList.get(0).getRate();
        if(strrating>0) {
            Animation pulse = AnimationUtils.loadAnimation(MainActivity.this, R.anim.pulse);
            r.startAnimation(pulse);
        }
       aRB1.setRatingsTo(app.session.ratingsList.get(0).getRate(),null);
    }*/

    /* @Override
    protected void onResume() {
       // aRB1.setRatingsTo(app.session.ratingsList.get(0).getRate(),null);

    }*/

    @Override
    public void ratingChanged(int ratingBarNo, int rating) {

        Log.e("Test","Ratings changed for q"+ratingBarNo+" to "+rating+" stars");
        rt=rating;

         FeedbackApp app = (FeedbackApp) getApplicationContext();
        app.session.ratingsList.get(0).setRating(rating);
        strrating=app.session.ratingsList.get(0).getRate();
        if(strrating>0) {
            Animation pulse = AnimationUtils.loadAnimation(MainActivity.this, R.anim.pulse);
            r.startAnimation(pulse);
             new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(MainActivity.this, Gender.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                overridePendingTransition(R.layout.slide_in_right, R.layout.slide_out_left);
            }
        }, 500);

        }
    }

    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        if (app.session.ratingsList != null)
                            app.session.ratingsList.clear();

                        finish();
                    }
                }).create().show();
    }
}