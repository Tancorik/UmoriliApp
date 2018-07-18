package com.example.tancorik.umoriliapp.presentation.view;

import com.example.tancorik.umoriliapp.presentation.model.CategoryModel;

import java.util.List;
import java.util.Map;

public interface IMainScreenView {
    void showCategory(List<CategoryModel> categoryModels);
    void showPosts(List<String> strings);
    void showError();
}
