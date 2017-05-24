package com.example.raghulsn.feedback_new.model;

/**
 * Created by raghul.sn on 25/10/16.
 */

public class Ratelist  {

    private int id;
    public Integer qid;
    public String qstn;
    public int rating;

    public Ratelist(int qstnid,String question, int rating) {

        this.qid=qstnid;
        this.qstn=question;
        this.rating=rating;

    }

    public int getcid(int cid)
    {
        return cid;
    }
}