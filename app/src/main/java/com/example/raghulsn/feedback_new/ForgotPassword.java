package com.example.raghulsn.feedback_new;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.raghulsn.feedback_new.model.FactoryClass;
import com.example.raghulsn.feedback_new.model.FeedbackApp;

/**
 * Created by raghul.sn on 23/3/17.
 */

public class ForgotPassword extends Activity {

    Button recover;
    ImageView left;
    String api;
    FactoryClass.ResponseMessage responseMessage;
    Handler handler;
    FactoryClass mFactory;
    private  final int SUCCESS = 200;
    private static final int API_FAIL = 401 ;
    EditText email;
    String to;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot);
        recover=(Button) findViewById(R.id.recover);
        left=(ImageView) findViewById(R.id.larw);
        email=(EditText) findViewById(R.id.mail);
        mFactory=FactoryClass.getInstance();

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what)
                {
                    case SUCCESS:

                        Toast.makeText(getApplicationContext(), "Successfully send the mail",Toast.LENGTH_SHORT).show();

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

        recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                api= "autoMailTriggering/";
                to=email.getText().toString();


                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            responseMessage = mFactory.mail(api,to);
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



            }
        });


        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ForgotPassword.this, Login.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

                overridePendingTransition(R.layout.slide_in_left, R.layout.slide_out_right);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();



        Animation pulse = AnimationUtils.loadAnimation(ForgotPassword.this, R.anim.pulse);
        left.startAnimation(pulse);

    }



    }
