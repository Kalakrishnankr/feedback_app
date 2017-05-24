package com.example.raghulsn.feedback_new;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by kalakrishnan.kr on 20/10/16.
 */
public class Addquestion extends Activity {

    ImageView i;
    TextView add;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.addquestions);
        i=(ImageView)findViewById(R.id.lfarw);
        add=(TextView)findViewById(R.id.txt3);
//        next= (Button) findViewById(R.id.imgV1_right);
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Addquestion.this, Settings.class);
                startActivity(intent);
                //overridePendingTransition(R.layout.slide_in_left, R.layout.slide_out_right);
                overridePendingTransition(R.layout.slide_in_left, R.layout.slide_out_right);
            }
        });

    }
}
