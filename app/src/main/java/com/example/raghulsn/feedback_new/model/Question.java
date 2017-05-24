package com.example.raghulsn.feedback_new.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by raghul.sn on 21/10/16.
 */

public class Question {

    private int id;
    private int cid;
    @SerializedName("ID")
    private int qid;
    private int rating;
    @SerializedName("Question_Desc")
    private  String question;




    public void rate(int id,int cid,int qid,int rate) {
        this.id = id;
        this.cid = cid;
        this.qid = qid;
       // this.rating = rate;


    }


    public Question(Integer qid, String qn) {

        this.question = qn;
        this.qid = qid;
        this.rating = 0;



    }

    public String getQuestion() {
        return question;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getQid()
    {
        return qid;
    }
    public int getRate()
    {
        return rating;
    }
}
