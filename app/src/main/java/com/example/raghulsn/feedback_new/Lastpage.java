package com.example.raghulsn.feedback_new;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by raghul.sn on 11/10/16.
 */

public class Lastpage extends Activity {

    ImageView l,home;
    TextView tv1,tv2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.lastpage);
        //i=(ImageView)findViewById(R.id.rgarw);
        tv1=(TextView)findViewById(R.id.txt2);
        tv2=(TextView)findViewById(R.id.txt3);

        l=(ImageView)findViewById(R.id.lfarw);
        home=(ImageView)findViewById(R.id.btnhome);



        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Lastpage.this, Contact.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                overridePendingTransition(R.layout.slide_in_left, R.layout.slide_out_right);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Lastpage.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                overridePendingTransition(R.layout.slide_in_left, R.layout.slide_out_right);
            }
        });

        tv1.postDelayed(new Runnable() {
            public void run() {
                tv1.setVisibility(View.INVISIBLE);
                tv2.setVisibility(View.VISIBLE);
            }
        }, 3000);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Animation pulse = AnimationUtils.loadAnimation(Lastpage.this, R.anim.pulse);
        l.startAnimation(pulse);
    }
}
