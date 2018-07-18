package com.example.tancorik.umoriliapp.presentation.model;

public class CategoryModel {

    private String mCategory;
    private String mDescription;

    public CategoryModel(String category, String description) {
        mCategory = category;
        mDescription = description;
    }

    public String getCategory() {
        return mCategory;
    }

    public String getDescription() {
        return mDescription;
    }
}
