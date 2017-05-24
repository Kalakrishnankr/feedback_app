package com.example.raghulsn.feedback_new;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raghulsn.feedback_new.model.DBHelper;
import com.example.raghulsn.feedback_new.model.FeedbackApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raghul.sn on 11/10/16.
 */

public class Rating extends Activity implements AnimatedRatingBar.OnRatingsChangedListner {

    ImageView l, r;
    TextView tv1, tv2, tv3, tv4;
    DBHelper db;
    RecyclerView recyclerView;
    FeedbackApp app;
    RecyclerAdapter mAdapter;
    Integer rt, pos, sz, rate;
    int flag, strrating;

    int ratingbar,ratingbartemp;


    AnimatedRatingBar aRB1, aRB2, aRB3, aRB4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.rating);
        app = (FeedbackApp) getApplicationContext();
        //final View rootView = inflater.inflate(R.layout.rating, container, false);
        r = (ImageView) findViewById(R.id.rgarw);
        aRB1 = (AnimatedRatingBar) findViewById(R.id.rb1);
        /*aRB2 = (AnimatedRatingBar) findViewById(R.id.rb2);
        aRB3 = (AnimatedRatingBar)Insertion success findViewById(R.id.rb3);
        aRB4 = (AnimatedRatingBar) findViewById(R.id.rb4);

        aRB1.ratingBarNo = 1;
        aRB2.ratingBarNo = 2;
        aRB3.ratingBarNo = 3;
        aRB4.ratingBarNo = 4;

        aRB1.onRatingsChangedListner = this;
        aRB2.onRatingsChangedListner = this;
        aRB3.onRatingsChangedListner = this;
        aRB4.onRatingsChangedListner = this;*/


        db = new DBHelper(this);
        /* boolean status1;
        RecyclerAdapter(boolean status)
        {
           status1=status;


        }*/

        sz = app.session.ratingsList.size();

        recyclerView = (RecyclerView) findViewById(R.id.lview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        l = (ImageView) findViewById(R.id.lfarw);


        if (app.session.ratingsList != null || app.session.ratingsList.size() > 0) {
            mAdapter = new RecyclerAdapter();
            recyclerView.setAdapter(mAdapter);
        }


        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(Rating.this, Gender.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                overridePendingTransition(R.layout.slide_in_left, R.layout.slide_out_right);
            }
        });

        r.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                flag = 0;
                for (int i = 1; i < sz; i++) {
                    rate = app.session.ratingsList.get(i).getRate();
                    if (rate == 0) {
                        flag = 1;
                    }
                }

                if (flag == 0) {

                    Intent i = new Intent(Rating.this, Contact.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    overridePendingTransition(R.layout.slide_in_right, R.layout.slide_out_left);
                } else {
                    Toast.makeText(Rating.this, "Please rate all", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        FeedbackApp.isSettingsScreen = false;

        Animation pulse = AnimationUtils.loadAnimation(Rating.this, R.anim.pulse);
        l.startAnimation(pulse);

        flag = 0;
        for (int i = 1; i < sz; i++) {
            rate = app.session.ratingsList.get(i).getRate();
            if (rate == 0) {
                flag = 1;
            }
        }

        if (flag == 0) {

            Animation pulse1 = AnimationUtils.loadAnimation(Rating.this, R.anim.pulse);
            r.startAnimation(pulse1);
        }




        // disply();
    }

    @Override
    public void ratingChanged(int ratingBarNo, int rating) {

        Log.e("Test", "Ratings changed for q" + ratingBarNo + " to " + rating + " stars");
        rt = rating;
        app.session.ratingsList.get(ratingBarNo + 1).setRating(rating);
        strrating = app.session.ratingsList.get(1).getRate();



        flag = 0;
        for (int i = 1; i < sz; i++) {
            rate = app.session.ratingsList.get(i).getRate();
            if (rate == 0) {
                flag = 1;
            }
        }

        if (flag == 0) {

            Animation pulse = AnimationUtils.loadAnimation(Rating.this, R.anim.pulse);
            r.startAnimation(pulse);
        }








    }


    public class RecyclerAdapter extends RecyclerView.Adapter<CustomViewHolder> {


        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_list, null);

            CustomViewHolder viewHolder = new CustomViewHolder(view);


            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final CustomViewHolder holder, final int position) {

            holder.qstn.setText(app.session.ratingsList.get(position + 1).getQuestion());
            holder.ratingBar.ratingBarNo = position;
            holder.ratingBar.onRatingsChangedListner = Rating.this;
           /* holder. dname.setText(qstnlst.get(position).dname);
            holder. id.setText(""+qstnlst.get(position).id);*/
            holder.ratingBar.setRatingsTo(app.session.ratingsList.get(position + 1).getRate(), null);


            //holder.ratingBar.onClick()
           /* holder.rlMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(Rating.this, "click", Toast.LENGTH_SHORT).show();
                   // holder.scrollToPositionWithOffset(2, 20);



                }
            });*/

            holder.ratingBar.setOnStarClickListener(new StarAnimationListener() {
                @Override
                public void onClick() {

                    final LinearLayoutManager layoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
                     final int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();

                    if(lastVisiblePosition <= (app.session.ratingsList.size() - 1)) {
                       if(position == lastVisiblePosition) {

                           new Handler().postDelayed(new Runnable() {
                               @Override
                               public void run() {


                                  // layoutManager.scrollToPositionWithOffset(position,lastVisiblePosition + 1);
                                   recyclerView.smoothScrollBy(0,100);


                               }
                           }, 500);
                           //recyclerView.smoothScrollToPosition(lastVisiblePosition + 1);
                        //  recyclerView.smoothScrollBy(0,100);


                       }
                    }



                }


            });

        }


        @Override
        public int getItemCount() {
            return app.session.ratingsList.size() - 1;
        }
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView qstn;
        TextView qid;

        AnimatedRatingBar ratingBar;

        RelativeLayout rlMain;

        public CustomViewHolder(View view) {
            super(view);

            qstn = (TextView) view.findViewById(R.id.qs1);
            //qid = (TextView)view.findViewById(R.id.qid);
            ratingBar = (AnimatedRatingBar) view.findViewById(R.id.rb1);
            rlMain = (RelativeLayout) view.findViewById(R.id.rl4);
        }
    }




    /*public void onpressed(final Integer va) {
        new AlertDialog.Builder(this.getActivity())
                .setTitle("Really Delete?")
                .setMessage("Are you sure you want to delete?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {

                        *//*db.deletestudent(va);*//*

                    }
                }).create().show();
    }
*/


}
