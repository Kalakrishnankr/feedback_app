package com.example.raghulsn.feedback_new.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by raghul.sn on 18/4/17.
 */

public class Ratinglist {


    @SerializedName("ID")
    public Integer qid;
    @SerializedName("TotalRating")
    public int totalrating;

    public Ratinglist(int qstnid , int rating) {

        this.qid=qstnid;

        this.totalrating=rating;

    }



}
