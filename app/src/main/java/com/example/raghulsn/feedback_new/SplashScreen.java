package com.example.raghulsn.feedback_new;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.example.raghulsn.feedback_new.model.FactoryClass;
import com.example.raghulsn.feedback_new.model.Sessionmanager;

/**
 * Created by raghul.sn on 23/3/17.
 */

public class SplashScreen extends Activity {



    Sessionmanager session;
    SharedPreferences pref;
    boolean Islogin;
    FactoryClass mFactory;

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash_screen);
        session = new Sessionmanager(getApplicationContext());
         pref = getSharedPreferences("Feedpref", 0);
         Islogin = pref.getBoolean("IsLoggedIn", false);
        mFactory=new FactoryClass(getApplicationContext());
        /* New Handler to start the Login-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Login-Activity. */


                if(!Islogin) {
                    Intent mainIntent = new Intent(SplashScreen.this, Login.class);
                    startActivity(mainIntent);
                    finish();
                }
                else {

                    Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                }

            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}