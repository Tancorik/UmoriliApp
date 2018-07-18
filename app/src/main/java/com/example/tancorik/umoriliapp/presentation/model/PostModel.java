package com.example.tancorik.umoriliapp.presentation.model;

import com.google.gson.annotations.SerializedName;

public class PostModel {

    @SerializedName("site")
    private String mSite;
    @SerializedName("name")
    private String mName;
    @SerializedName("elementPureHtml")
    private String mText;

    public String getSite() {
        return mSite;
    }

    public void setSite(String site) {
        mSite = site;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }
}
