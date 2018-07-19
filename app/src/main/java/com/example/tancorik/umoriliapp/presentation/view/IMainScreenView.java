package com.example.tancorik.umoriliapp.presentation.view;

import com.example.tancorik.umoriliapp.presentation.model.CategoryModel;

import java.util.List;

public interface IMainScreenView {
    void showCategory(List<CategoryModel> categoryModels);
    void showPosts(List<String> strings);
    void showError();
}
