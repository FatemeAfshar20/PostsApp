package com.example.postsapp.model;

import android.util.Log;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class FlikerPost {
    private String mId;
    private String mTitle;
    private String mUrlSmall;
    private long mDateUpload;

    public FlikerPost(String title, String urlSmall, String dateUpload,String id) {
        mTitle = title;
        mUrlSmall = urlSmall;
        mId=id;
        setDateUpload(dateUpload);
    }

    public FlikerPost() {
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getUrlSmall() {
        return mUrlSmall;
    }

    public void setUrlSmall(String urlSmall) {
        mUrlSmall = urlSmall;
    }

    public String getDateUpload() {

        return new String();
    }

    public void setDateUpload(String dateUpload) {

    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }
}
