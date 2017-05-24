package com.example.raghulsn.feedback_new;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.raghulsn.feedback_new.model.Customer;
import com.example.raghulsn.feedback_new.model.DBHelper;
import com.example.raghulsn.feedback_new.model.FeedbackApp;
import com.example.raghulsn.feedback_new.model.Session;

/**
 * Created by raghul.sn on 12/10/16.
 */

public class Gender extends Activity {

    ImageView r, l, male, female;
    Button b1, b2, b3, b4;
    boolean isPressed = true;
    boolean isPressed1 = true;
    boolean isPressed2 = true;
    boolean isPressed3 = true;

    DBHelper db;
    String MY_PREFS_NAME;
    public Session session;
    public Customer customer;
    String agedata;
    String sexdata;
    int flagresume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        flagresume=0;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.gender);
        b1 = (Button) findViewById(R.id.btn1);
        b2 = (Button) findViewById(R.id.btn2);
        b3 = (Button) findViewById(R.id.btn3);
        b4 = (Button) findViewById(R.id.btn4);
        r = (ImageView) findViewById(R.id.rgarw);
        l = (ImageView) findViewById(R.id.larw);
        male = (ImageView) findViewById(R.id.imgMan);
        female = (ImageView) findViewById(R.id.imgGrl);
        final FeedbackApp app1 = (FeedbackApp) getApplicationContext();



        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(!agedata.trim().isEmpty() &&  !sexdata.trim().isEmpty()) {


                    app1.session.customer.gender(agedata, sexdata);


                    Intent i = new Intent(Gender.this, Rating.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    overridePendingTransition(R.layout.slide_in_right, R.layout.slide_out_left);

                }

                else
                {
                    Toast.makeText(Gender.this, "Please select age and sex", Toast.LENGTH_SHORT).show();
                }
            }
        });

        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Gender.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

                overridePendingTransition(R.layout.slide_in_left, R.layout.slide_out_right);
            }
        });


        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // if(isPressed) {
                b1.setBackgroundResource(R.drawable.buttons01);
                b2.setBackgroundResource(R.drawable.button1);
                b3.setBackgroundResource(R.drawable.button1);
                b4.setBackgroundResource(R.drawable.button1);
                agedata = "b1";

                if(flagresume==0) {
                    if (!agedata.trim().isEmpty() && !sexdata.trim().isEmpty()) {

                        app1.session.customer.gender(agedata, sexdata);

                        Animation pulse = AnimationUtils.loadAnimation(Gender.this, R.anim.pulse);
                        r.startAnimation(pulse);
                        Intent i = new Intent(Gender.this, Rating.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);

                        overridePendingTransition(R.layout.slide_in_right, R.layout.slide_out_left);
                    }
                }

               /* }
                else
                    b1.setBackgroundResource(R.drawable.button1);

                isPressed = !isPressed;*/

            }
        });


        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /*if(isPressed1) {*/
                b2.setBackgroundResource(R.drawable.buttons01);
                b1.setBackgroundResource(R.drawable.button1);
                b3.setBackgroundResource(R.drawable.button1);
                b4.setBackgroundResource(R.drawable.button1);
                agedata = "b2";

                if(flagresume==0) {

                    if (!agedata.trim().isEmpty() && !sexdata.trim().isEmpty()) {

                        app1.session.customer.gender(agedata, sexdata);
                        Animation pulse = AnimationUtils.loadAnimation(Gender.this, R.anim.pulse);
                        r.startAnimation(pulse);

                        Intent i = new Intent(Gender.this, Rating.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);

                        overridePendingTransition(R.layout.slide_in_right, R.layout.slide_out_left);
                    }
                }

               /* }
                else
                    b2.setBackgroundResource(R.drawable.button1);

                isPressed1 = !isPressed1;*/

            }
        });

        b3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               /* if(isPressed2) {*/
                b3.setBackgroundResource(R.drawable.buttons01);
                b2.setBackgroundResource(R.drawable.button1);
                b1.setBackgroundResource(R.drawable.button1);
                b4.setBackgroundResource(R.drawable.button1);
                agedata = "b3";

                if(flagresume==0) {
                    if (!agedata.trim().isEmpty() && !sexdata.trim().isEmpty()) {

                        app1.session.customer.gender(agedata, sexdata);
                        Animation pulse = AnimationUtils.loadAnimation(Gender.this, R.anim.pulse);
                        r.startAnimation(pulse);
                        Intent i = new Intent(Gender.this, Rating.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);

                        overridePendingTransition(R.layout.slide_in_right, R.layout.slide_out_left);
                    }
                }

                /*}

                else
                    b3.setBackgroundResource(R.drawable.button1);

                isPressed2 = !isPressed2;*/

            }
        });

        b4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // if(isPressed3) {
                b4.setBackgroundResource(R.drawable.buttons01);
                b2.setBackgroundResource(R.drawable.button1);
                b3.setBackgroundResource(R.drawable.button1);
                b1.setBackgroundResource(R.drawable.button1);
                agedata = "b4";

                if(flagresume==0) {
                    if (!agedata.trim().isEmpty() && !sexdata.trim().isEmpty()) {

                        app1.session.customer.gender(agedata, sexdata);
                        Animation pulse = AnimationUtils.loadAnimation(Gender.this, R.anim.pulse);
                        r.startAnimation(pulse);
                        Intent i = new Intent(Gender.this, Rating.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);

                        overridePendingTransition(R.layout.slide_in_right, R.layout.slide_out_left);
                    }
                }

                /*}
                else
                    b4.setBackgroundResource(R.drawable.button1);

                isPressed3 = !isPressed3;*/

            }
        });


        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String val = String.valueOf(male);
                female.setImageResource(R.drawable.female1);
                male.setImageResource(R.drawable.male);
                sexdata = "female";
                //female.setTag(2);
                Toast.makeText(Gender.this, "Female", Toast.LENGTH_SHORT).show();

                if(flagresume==0) {

                    if (!agedata.trim().isEmpty() && !sexdata.trim().isEmpty()) {

                        app1.session.customer.gender(agedata, sexdata);
                        Animation pulse = AnimationUtils.loadAnimation(Gender.this, R.anim.pulse);
                        r.startAnimation(pulse);
                        Intent i = new Intent(Gender.this, Rating.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);

                        overridePendingTransition(R.layout.slide_in_right, R.layout.slide_out_left);
                    }
                }

            }
        });

        // male.setTag(1);
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                male.setImageResource(R.drawable.male1);
                female.setImageResource(R.drawable.female);
                Toast.makeText(Gender.this, "Male", Toast.LENGTH_SHORT).show();
                sexdata = "male";

                if(flagresume==0) {
                    if (!agedata.trim().isEmpty() && !sexdata.trim().isEmpty()) {

                        app1.session.customer.gender(agedata, sexdata);
                        Animation pulse = AnimationUtils.loadAnimation(Gender.this, R.anim.pulse);
                        r.startAnimation(pulse);
                        Intent i = new Intent(Gender.this, Rating.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);

                        overridePendingTransition(R.layout.slide_in_right, R.layout.slide_out_left);
                    }
                }


                //male.setTag(2);
                /*}else{
                    male.setImageResource(R.drawable.male);
                    male.setTag(1);
                }*/
            }
        });


    }


    public void onResume() {
        super.onResume();



       /* final FeedbackApp app = (FeedbackApp) getApplicationContext();*/

        final FeedbackApp app = (FeedbackApp) getApplicationContext();
        agedata=app.session.customer.getAge().toString();
        sexdata=app.session.customer.getSex().toString();
        if (agedata == "b1") {
            b1.setBackgroundResource(R.drawable.buttons01);
            b2.setBackgroundResource(R.drawable.button1);
            b3.setBackgroundResource(R.drawable.button1);
            b4.setBackgroundResource(R.drawable.button1);
        }if (agedata == "b2") {
            b2.setBackgroundResource(R.drawable.buttons01);
            b1.setBackgroundResource(R.drawable.button1);
            b3.setBackgroundResource(R.drawable.button1);
            b4.setBackgroundResource(R.drawable.button1);
        }if (agedata == "b3")  {
            b3.setBackgroundResource(R.drawable.buttons01);
            b2.setBackgroundResource(R.drawable.button1);
            b1.setBackgroundResource(R.drawable.button1);
            b4.setBackgroundResource(R.drawable.button1);
        }if(agedata == "b4")
        {
            b4.setBackgroundResource(R.drawable.buttons01);
            b2.setBackgroundResource(R.drawable.button1);
            b3.setBackgroundResource(R.drawable.button1);
            b1.setBackgroundResource(R.drawable.button1);
        }


        if(sexdata=="male")
        {
            male.setImageResource(R.drawable.male1);
            female.setImageResource(R.drawable.female);
        }
        if(sexdata=="female"){
            female.setImageResource(R.drawable.female1);
            male.setImageResource(R.drawable.male);
        }

        if(!agedata.trim().isEmpty() &&  !sexdata.trim().isEmpty()) {

            Animation pulse = AnimationUtils.loadAnimation(Gender.this, R.anim.pulse);
            r.startAnimation(pulse);
            flagresume=1;
        }

        Animation pulse = AnimationUtils.loadAnimation(Gender.this, R.anim.pulse);
        l.startAnimation(pulse);

    }
}