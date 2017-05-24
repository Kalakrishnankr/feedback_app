package com.example.raghulsn.feedback_new;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.raghulsn.feedback_new.model.FactoryClass;
import com.example.raghulsn.feedback_new.model.Sessionmanager;

import org.json.JSONObject;

import static com.example.raghulsn.feedback_new.model.Sessionmanager.KEY_NAME;
import static com.example.raghulsn.feedback_new.model.Sessionmanager.KEY_PASS;
import static com.example.raghulsn.feedback_new.model.Sessionmanager.PREF_NAME;

/**
 * Created by raghul.sn on 27/3/17.
 */

public class ResetPassword extends Activity {

    ImageView larw;
    EditText ed1,ed2,ed3;
    Button sub;
    String newpass;
    String uname,pass;
    String api;
    SharedPreferences shared;
    Sessionmanager session;
    FactoryClass.ResponseMessage responseMessage;
    Handler handler;
    FactoryClass mFactory;
    private  final int SUCCESS = 200;
    private static final int API_FAIL = 401 ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset);
        larw=(ImageView) findViewById(R.id.lfarw);
        ed1=(EditText) findViewById(R.id.edit_Txt1);
        ed2=(EditText) findViewById(R.id.edit_Txt2);
        ed3=(EditText) findViewById(R.id.edit_Txt3);
        sub=(Button) findViewById(R.id.btnSubmit);
        mFactory=new FactoryClass(getApplicationContext());
        session=new Sessionmanager(getApplicationContext());
        shared = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        uname = (shared.getString(KEY_NAME, ""));
        pass= (shared.getString(KEY_PASS, ""));

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what)
                {
                    case SUCCESS:

                        ed1.setText("");
                        ed2.setText("");
                        ed3.setText("");
                        session.createLoginSession(uname,newpass);
                        Toast.makeText(getApplicationContext(), "Password is changed",Toast.LENGTH_SHORT).show();
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


        larw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(ResetPassword.this,Settings.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                //overridePendingTransition(R.layout.bounce1, R.layout.bounce1);
                overridePendingTransition(R.layout.slide_in_left, R.layout.slide_out_right);
            }
        });

        sub.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                api= "ChangeUserPassword/";

                newpass=ed3.getText().toString();

                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObj = new JSONObject();
                            jsonObj.put("newPassword",newpass);
                            responseMessage = mFactory.changepass(api,uname,pass,jsonObj);
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

                /*cupass=ed1.getText().toString();
                if(!cupass.equals("admin")){

                    ed1.requestFocus();
                    ed1.setError("Incorrect Password");
                }*/

            }
        });

    }
}
