package com.example.raghulsn.feedback_new.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kalakrishnan.kr on 21/10/16.
 */
public class Questionlist {
    @SerializedName("ID")
    public Integer columnId;
    @SerializedName("Question_Desc")
    public String questn;

    public Questionlist(Integer id, String qstn) {

        this.columnId = id;
        this.questn = qstn;


    }
}
