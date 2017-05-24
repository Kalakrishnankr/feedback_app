package com.example.raghulsn.feedback_new;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raghulsn.feedback_new.model.Base64Coder;
import com.example.raghulsn.feedback_new.model.CustomTextview;
import com.example.raghulsn.feedback_new.model.FactoryClass;
import com.example.raghulsn.feedback_new.model.FeedbackApp;
import com.example.raghulsn.feedback_new.model.Question;
import com.example.raghulsn.feedback_new.model.Questions;
import com.example.raghulsn.feedback_new.model.Qustnrating;
import com.example.raghulsn.feedback_new.model.Sessionmanager;
import com.google.gson.Gson;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

/**
 * Created by raghul.sn on 22/3/17.
 */

public class Login extends Activity{



    EditText uname;
    EditText pass;
    CustomTextview forgot;
    TextView txtinval;
    String name;
    String password;
    FeedbackApp app;
    String text;
    Sessionmanager getsession;
    String api;
    FactoryClass.ResponseMessage responseMessage;
    Handler handler;
    FactoryClass flogin;
    private  final int SUCCESS = 200;
    private static final int API_FAIL = 401 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        app=(FeedbackApp) getApplicationContext();
        uname = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        txtinval = (TextView) findViewById(R.id.invalid);
        forgot = (CustomTextview) findViewById(R.id.forgot);
        getsession = new Sessionmanager(getApplicationContext());
        flogin= new FactoryClass(getApplicationContext());

         handler = new Handler(new Handler.Callback() {
             @Override
             public boolean handleMessage(Message msg) {
                 switch (msg.what)
                 {
                     case SUCCESS:

                         getsession.createLoginSession(name,password);
                         Intent i = new Intent(Login.this, MainActivity.class);
                         i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                         startActivity(i);
                         overridePendingTransition(R.layout.slide_in_right, R.layout.slide_out_left);

                         //Toast.makeText(getApplicationContext(), responseMessage.message,Toast.LENGTH_SHORT).show();
                         break;


                     case API_FAIL:
                         txtinval.setVisibility(View.VISIBLE);
                        // Toast.makeText(getApplicationContext(),"API Failure...",Toast.LENGTH_SHORT).show();
                         break;
                     default:
                         //txtinval.setVisibility(View.VISIBLE);
                         Toast.makeText(getApplicationContext(),"API Failure...",Toast.LENGTH_SHORT).show();
                         break;

                 }
                 return false;
             }
         });



        pass.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_DONE){

                    name = uname.getText().toString();
                    password = pass.getText().toString();

                    api= "authenticateUserLogin/";
                    //api= "http://10.0.2.2:57472/";
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                responseMessage = flogin.executeRequest(api,name,password);
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


                    /*if(name.isEmpty() && password.isEmpty())
                        {
                            txtinval.setVisibility(View.VISIBLE);
                            //Toast.makeText(getApplicationContext(), "Invalid username and password", Toast.LENGTH_SHORT).show();
                        }
                    if (name.equals("admin") && password.equals("admin"))
                    {

                        session.createLoginSession("admin", "admin");

                        Intent i = new Intent(Login.this, MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        overridePendingTransition(R.layout.slide_in_right, R.layout.slide_out_left);
                    }
                    else
                        {
                            txtinval.setVisibility(View.VISIBLE);
                            //Toast.makeText(getApplicationContext(), "Invalid username and password", Toast.LENGTH_SHORT).show();
                        }*/
                    }
                return false;
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               forgot.setLinkTextColor(0xFFFF0000);
                Intent i = new Intent(Login.this, ForgotPassword.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

                overridePendingTransition(R.layout.slide_in_left, R.layout.slide_out_right);
            }
        });


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


    /*private ResponseMessage  executeRequest(String urlString) throws JSONException, IOException, GeneralSecurityException {

        ResponseMessage responseMessage = null;
        String REQUEST;
        String json = "";
        HttpURLConnection conn = null;
        int statusCode;
        InputStream in = null;
        OutputStream os;

        try {
            responseMessage = new ResponseMessage();
            URL httpUrl = new URL(urlString);
            conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setReadTimeout(60*1000 *//*milliseconds*//*);
            conn.setConnectTimeout(60*1000 *//* milliseconds *//*);
            System.setProperty("http.keepAlive", "false");
            conn.setRequestMethod("GET");
            conn.setRequestProperty("X-PartnerKey","qagate");
            conn.setRequestProperty("Authorization","Basic "+Base64Coder.encodeString(name + ":" + password));

            conn.setDoInput(true);
            conn.setDoOutput(false);
            conn.connect();
            statusCode = conn.getResponseCode();
            BufferedReader reader;

            if (statusCode == 200 || statusCode == 201) {

                in = new BufferedInputStream(conn.getInputStream());
                reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                json = reader.readLine();
                responseMessage.message = json;
                responseMessage.responseCode = statusCode;
            } else {
                responseMessage.message = json;
                responseMessage.responseCode = statusCode;
                in = new BufferedInputStream(conn.getErrorStream());
                reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                json = reader.readLine();
            }

        } catch (Exception e) {

            if (e.getMessage().contains("authentication challenge")) {
            } else {
                if (conn != null) {
                }
            }

        } finally {
            if (conn != null)
                conn.disconnect();
            if (in != null) {
                in.close();
            }

        }
        return responseMessage;
    }
    public static  class ResponseMessage
    {
        int responseCode;
        String message;


    }*/
}
