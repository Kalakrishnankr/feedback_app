package com.example.raghulsn.feedback_new.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raghul.sn on 20/4/17.
 */

public class JsonSampleParser {
    public JSONObject jsonOuterObj = new JSONObject();
    JSONArray jsonArray = new JSONArray();

    JSONObject innerJsonObject = new JSONObject();
    void jsonParsing()
    {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    jsonOuterObj.put("phoneNumber", 111122);
                    jsonOuterObj.put("gmail", "ssssss.s@jj.com");
                    jsonOuterObj.put("name", "Unice");
                    jsonOuterObj.put("gender", "M");
                    jsonOuterObj.put("phoneNumber", 111122);
                    jsonOuterObj.put("gmail", "ssssss.s@jj.com");
                    jsonOuterObj.put("age", 25);
                    jsonOuterObj.put("customerId", 1);
                    innerJsonObject.put("ID", 01);
                    innerJsonObject.put("ratings", "10");
                    innerJsonObject.put("ID", 02);
                    innerJsonObject.put("ratings", "20");
                    jsonArray.put(innerJsonObject);
                    jsonOuterObj.put("rating", jsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        System.out.println(jsonOuterObj.toString());
    }

    public static void main(String args[]) throws JSONException {

       new JsonSampleParser().jsonParsing();


    }



}
