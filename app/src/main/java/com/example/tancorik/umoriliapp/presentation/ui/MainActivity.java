package com.example.tancorik.umoriliapp.presentation.ui;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.tancorik.umoriliapp.R;
import com.example.tancorik.umoriliapp.application.UmoriliApp;
import com.example.tancorik.umoriliapp.presentation.model.CategoryModel;
import com.example.tancorik.umoriliapp.presentation.presenter.MainScreenPresenter;
import com.example.tancorik.umoriliapp.presentation.view.IMainScreenView;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements IMainScreenView, PostsRecyclerAdapter.AdapterCallback {

    @Inject MainScreenPresenter mPresenter;
    private TabLayout mTabLayout;
    private PostsRecyclerAdapter mPostsRecyclerAdapter;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UmoriliApp.getComponent().inject(this);
        init();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void showCategory(List<CategoryModel> categoryModels) {
        initTabLayout(categoryModels);
    }

    @Override
    public void showPosts(List<String> strings) {
        mProgressBar.setVisibility(View.INVISIBLE);
        mPostsRecyclerAdapter.setPostList(strings);
        mPostsRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        ErrorDialog dialog = new ErrorDialog();
        dialog.show(getSupportFragmentManager(), ErrorDialog.TAG);
    }

    @Override
    public void onPostClick(int position) {
        mPresenter.setSelectedPost(position);
        ClipboardDialog clipboardDialog = new ClipboardDialog();
        clipboardDialog.show(getSupportFragmentManager(), ClipboardDialog.TAG);
    }

    private void init() {
        mTabLayout = findViewById(R.id.category_tab_layout);
        mPresenter.attachView(this);
        mPresenter.getCategory();

        mProgressBar = findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.VISIBLE);

        mPostsRecyclerAdapter = new PostsRecyclerAdapter();
        mPostsRecyclerAdapter.setCallback(this);
        mRecyclerView = findViewById(R.id.posts_recycler_view);
        mRecyclerView.setAdapter(mPostsRecyclerAdapter);
        List<String> stringList = mPresenter.getCurrentPosts();
        if (stringList.size() > 0) {
            showPosts(stringList);
        }
        else {
            mPresenter.getNewPosts(mTabLayout.getSelectedTabPosition());
        }
    }

    private void initTabLayout(List<CategoryModel> categoryModels) {
        for (CategoryModel categoryModel : categoryModels) {
            TabLayout.Tab tab = mTabLayout.newTab();
            tab.setText(categoryModel.getDescription());
            mTabLayout.addTab(tab);
        }
        TabLayout.Tab tab = mTabLayout.getTabAt(mPresenter.getTabPosition());
        if (tab != null) {
            tab.select();
        }
        mTabLayout.addOnTabSelectedListener(new OnTabListener());
    }

    private class OnTabListener implements TabLayout.OnTabSelectedListener {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            mProgressBar.setVisibility(View.VISIBLE);
            mPresenter.getNewPosts(tab.getPosition());
            mRecyclerView.scrollToPosition(0);
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
            mRecyclerView.scrollToPosition(0);
        }
    }
}
