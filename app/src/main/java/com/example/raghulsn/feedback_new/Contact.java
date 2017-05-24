package com.example.raghulsn.feedback_new;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.raghulsn.feedback_new.model.Customer;
import com.example.raghulsn.feedback_new.model.DBHelper;
import com.example.raghulsn.feedback_new.model.FactoryClass;
import com.example.raghulsn.feedback_new.model.FeedbackApp;
import com.example.raghulsn.feedback_new.model.Sessionmanager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;

import static com.example.raghulsn.feedback_new.model.Sessionmanager.KEY_NAME;
import static com.example.raghulsn.feedback_new.model.Sessionmanager.KEY_PASS;
import static com.example.raghulsn.feedback_new.model.Sessionmanager.PREF_NAME;

/**
 * Created by raghul.sn on 11/10/16.
 */

public class Contact extends Activity implements TextWatcher {

    ImageView l, r;
    Button submit;
    EditText ed1, ed2, ed3;
    String name, email, phone, sexdata, agedata;
    Integer qid=0;
    Integer rt=0,sz;
    DBHelper db;
    String MY_PREFS_NAME;
    String dateTime;
    String uname,pass;
    String api;
    private CharSequence text;
    FeedbackApp app;
    Sessionmanager getsession;
    SharedPreferences shared;
    FactoryClass.ResponseMessage responseMessage;
    Handler handler;
    FactoryClass mFactory;
    private  final int SUCCESS = 200;
    private static final int API_FAIL = 401 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.contact);
        //i=(ImageView)findViewById(R.id.rgarw);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        l = (ImageView) findViewById(R.id.lfarw);
        r = (ImageView) findViewById(R.id.rgarw);
        app= (FeedbackApp) getApplicationContext();
        submit = (Button) findViewById(R.id.btnSubmit);
        ed1 = (EditText) findViewById(R.id.edit_Txt1);
        ed2 = (EditText) findViewById(R.id.edit_Txt2);
        ed3 = (EditText) findViewById(R.id.edit_Txt3);

        ed1.addTextChangedListener(this);
        ed2.addTextChangedListener(this);
        ed3.addTextChangedListener(this);
        db = new DBHelper(Contact.this);
        mFactory=new FactoryClass(getApplicationContext());
        getsession=new Sessionmanager(getApplicationContext());
        shared = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        uname = (shared.getString(KEY_NAME, ""));
        pass= (shared.getString(KEY_PASS, ""));
        api="insertRatings/";


        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what)
                {
                    case SUCCESS:

                        Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "Rating completed",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Contact.this, Lastpage.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        overridePendingTransition(R.layout.slide_in_right, R.layout.slide_out_left);
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



        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Contact.this, Rating.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                overridePendingTransition(R.layout.slide_in_left, R.layout.slide_out_right);
            }
        });

        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submitForm();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                submitForm();

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        Animation pulse = AnimationUtils.loadAnimation(Contact.this, R.anim.pulse);
        l.startAnimation(pulse);


        name=app.session.customer.getName().toString();
        email=app.session.customer.getEmail().toString();
        phone=app.session.customer.getPhone().toString();

        ed1.setText(name);
        ed2.setText(email);
        ed3.setText(phone);
    }

    private void submitForm() {
        if (!validateName()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        if (!validatePhone()) {
            return;
        }

        long cid=0;
        long rid=0;
        final String email = ed2.getText().toString().trim();
        final String name = ed1.getText().toString().trim();
        final String phone = ed3.getText().toString().trim();

        submit.setBackgroundResource(R.drawable.buttons01);


       /* SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        sexdata = prefs.getString("sex", "no name");
        agedata = prefs.getString("age", "no name");*/
        //app = (FeedbackApp) getApplicationContext();
        app.session.customer.contact(name,email,phone);
        agedata=app.session.customer.getAge().toString();
        sexdata=app.session.customer.getSex().toString();
        dateTime = DateFormat.getDateTimeInstance().format(new Date());
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                     JSONObject jsonOuterObj = new JSONObject();

                    jsonOuterObj.put("phoneNumber",phone);
                    jsonOuterObj.put("gmail",email);

                    jsonOuterObj.put("name",name);
                    jsonOuterObj.put("gender",sexdata);

                    jsonOuterObj.put("age",agedata);


                    JSONArray jsonArray = new JSONArray();

                    JSONObject
                            innerJsonObject;
                    sz=app.session.ratingsList.size();
                    for(int i=0;i<sz;i++) {
                        innerJsonObject = new JSONObject();
                        rt = app.session.ratingsList.get(i).getRate();
                        qid= app.session.ratingsList.get(i).getQid();

                        innerJsonObject.put("ID",qid);
                        innerJsonObject.put("ratings",rt);
                        jsonArray.put(innerJsonObject);
                    }

                    jsonOuterObj.put("rating",jsonArray);

                    responseMessage = mFactory.changepass(api,uname,pass,jsonOuterObj);
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



           // sz=app.session.ratingsList.size();
            //qid=app.session.qstn.getQid();


            // rt=app.session.qstn.getRate();




    }

    private boolean validateName() {
        if (ed1.getText().toString().trim().isEmpty()) {
            ed1.setError("Enter Name");
            submit.setBackgroundResource(R.drawable.button1);

            //requestFocus(inputName);
            return false;
        } else {
            // ed1.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        String email = ed2.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            ed2.setError("Enter email");
            submit.setBackgroundResource(R.drawable.button1);

            // requestFocus(inputEmail);
            return false;
        } else {
            //ed1.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePhone() {

        phone = ed3.getText().toString().trim();
        boolean check=false;
        if(phone.length() < 10 )
        {
            check = false;
            ed3.setError("Enter 10 or more  digit phone number");
            submit.setBackgroundResource(R.drawable.button1);

        }
        else
        {
           /* Animation pulse = AnimationUtils.loadAnimation(Contact.this, R.anim.pulse);
            r.startAnimation(pulse);*/
            check = true;
        }

        return check;
    }

    private boolean isvalidatePhone(String ph) {


        boolean check=false;
        if(ph.length() < 10 )
        {
            check = false;

        }
        else
        {
           /* Animation pulse = AnimationUtils.loadAnimation(Contact.this, R.anim.pulse);
            r.startAnimation(pulse);*/
            check = true;
        }

        return check;
    }


    /*private boolean validatePhone() {

        phone = ed3.getText().toString().trim();
        boolean check=false;
        if(phone.isEmpty()|| !isValidphone(phone))
        {
            check = false;
            ed3.setError("Not Valid Number");
            submit.setBackgroundResource(R.drawable.button1);

        }
        else
        {
            *//*Animation pulse = AnimationUtils.loadAnimation(Contact.this, R.anim.pulse);
            r.startAnimation(pulse);
            check = true;*//*
        }

        return check;
    }*/


    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /*private static boolean isValidphone(String phone) {
        return !TextUtils.isEmpty(phone) && Patterns.PHONE.matcher(phone).matches();

    }*/


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        app.session.customer.setName(ed1.getText().toString());
        app.session.customer.setEmail(ed2.getText().toString());
        app.session.customer.setPhone(ed3.getText().toString());


        Customer customer = app.session.customer;

        if (!customer.getName().isEmpty()&&!customer.getPhone().isEmpty()&&!customer.getEmail().isEmpty()&&isValidEmail(customer.getEmail())&&isvalidatePhone(customer.getPhone())) {


                if (r.getAnimation() != null) return;
                Animation pulse = AnimationUtils.loadAnimation(Contact.this, R.anim.pulse);
                r.startAnimation(pulse);
            } else {
                r.clearAnimation();
            }


    }

}

