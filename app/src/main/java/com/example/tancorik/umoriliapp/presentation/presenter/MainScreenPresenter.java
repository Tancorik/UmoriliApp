package com.example.tancorik.umoriliapp.presentation.presenter;

import android.support.annotation.NonNull;
import android.text.Html;

import com.example.tancorik.umoriliapp.application.UmoriliApp;
import com.example.tancorik.umoriliapp.domain.IRemotePostService;
import com.example.tancorik.umoriliapp.domain.IRemotePostServiceCallback;
import com.example.tancorik.umoriliapp.presentation.model.CategoryModel;
import com.example.tancorik.umoriliapp.presentation.model.PostModel;
import com.example.tancorik.umoriliapp.presentation.view.IMainScreenView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainScreenPresenter {

    private static final String POSTS_ERROR_MESSAGE = "Ошибка сервера или нет интернета!";

    private IMainScreenView mView;
    private List<String> mPostList;
    private List<CategoryModel> mCategoryModels;
    private int mTabPosition;
    private int mSelectedPostNumber;
    @Inject IRemotePostService mRemotePostService;

    public MainScreenPresenter() {
        UmoriliApp.getComponent().inject(this);
        initCategoryList();
        mPostList = new ArrayList<>();
    }

    public void attachView(@NonNull IMainScreenView screenView) {
        mView = screenView;
    }

    public void detachView() {
        mView = null;
    }

    public String getErrorMessage() {
        return POSTS_ERROR_MESSAGE;
    }

    public void getCategory() {
        mView.showCategory(mCategoryModels);
    }

    public void setSelectedPost(int position) {
        mSelectedPostNumber = position;
    }

    public String getSelectedPost() {
        return mPostList.get(mSelectedPostNumber);
    }

    public List<String> getCurrentPosts() {
        return mPostList;
    }

    public int getTabPosition() {
        return mTabPosition;
    }

    public void getNewPosts(int position) {
        mTabPosition = position;
        String category = mCategoryModels.get(position).getCategory();
        mRemotePostService.getPostInfoByRubric(category, new IRemotePostServiceCallback() {
            @Override
            public void onSuccess(List<PostModel> postingList) {
                mPostList.clear();
                for(PostModel postModel : postingList) {
                    mPostList.add(Html.fromHtml(postModel.getText()).toString());
                }
                mView.showPosts(mPostList);
            }

            @Override
            public void onError(Throwable error) {
                mView.showError();
            }
        });
    }

    private void initCategoryList() {
        mCategoryModels = new ArrayList<>();
        mCategoryModels.add(new CategoryModel("bash", "bash"));
        mCategoryModels.add(new CategoryModel("new+anekdot", "Анекдоты"));
        mCategoryModels.add(new CategoryModel("new+aforizm", "Афоризмы"));
    }
}
