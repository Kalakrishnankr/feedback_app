package com.example.raghulsn.feedback_new.model;

import android.app.Application;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;

import io.fabric.sdk.android.Fabric;

/**
 * Created by kalakrishnan.kr on 20/10/16.
 */
public class FeedbackApp extends Application{

    public Session session;
    public Customer custom;
    public Question rating;
    public static boolean isSettingsScreen= false;
    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics(), new Answers());

        session=new Session(this);


    }


}
