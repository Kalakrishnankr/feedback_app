package com.example.raghulsn.feedback_new.model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.example.raghulsn.feedback_new.Login;
import com.example.raghulsn.feedback_new.MainActivity;
import com.example.raghulsn.feedback_new.R;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.example.raghulsn.feedback_new.model.Sessionmanager.KEY_NAME;
import static com.example.raghulsn.feedback_new.model.Sessionmanager.KEY_PASS;
import static com.example.raghulsn.feedback_new.model.Sessionmanager.PREF_NAME;

/**
 * Created by kalakrishnan.kr on 20/10/16.
 */
public class Session {

    public Customer customer;
    public Question qstn;
    private int id;
    private int q_id;
    private String question;
    public ArrayList<Question> ratingsList;

Context context;
    DBHelper dbHelper,db;

    public void setQ_id(int qid)

    {
        q_id = qid;
    }

    public Session(Context context) {
        customer = new Customer();
        //qstn =new Question();
        ratingsList = new ArrayList<Question>();
        this.context = context;
        dbHelper = new DBHelper(context);
        db= new DBHelper(context);


        db. insertQNDetails("1","Overall Rating");
        db. insertQNDetails("2", "Quality of staff");
        db. insertQNDetails("3", "Service by staff");
        db. insertQNDetails("4","Hotel ambience");
        db. insertQNDetails("5","Quality of food");
        db. insertQNDetails("6","food 1");
        db. insertQNDetails("7","food 2");
        db. insertQNDetails("8","food 3");
        db. insertQNDetails("9","food 4");
        db. insertQNDetails("10","food 5");
        /*db. insertQNDetails("1","Total Customer");
        db. insertQNDetails("2","Overall Rating");
        db. insertQNDetails("3", "Quality of staff");
        db. insertQNDetails("4", "Service by staff");
        db. insertQNDetails("5","Hotel ambience");*/
       /* db. insertQNDetails("6","Quality of food");*/





     /*   this.ratingsList = dbHelper.getAllQuestions();*/



    }
}