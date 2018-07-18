package com.example.tancorik.umoriliapp.presentation.ui;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.tancorik.umoriliapp.R;
import com.example.tancorik.umoriliapp.presentation.model.CategoryModel;
import com.example.tancorik.umoriliapp.presentation.presenter.MainScreenPresenter;
import com.example.tancorik.umoriliapp.presentation.view.IMainScreenView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainScreenView{

    private MainScreenPresenter mPresenter;
    private TabLayout mTabLayout;
    private PostsRecyclerAdapter mPostsRecyclerAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        mPostsRecyclerAdapter.setPostList(strings);
        mPostsRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        ErrorDialog dialog = new ErrorDialog();
        dialog.show(getSupportFragmentManager(), dialog.getTag());
    }

    private void init() {
        mTabLayout = findViewById(R.id.category_tab_layout);
        mPresenter = MainScreenPresenter.getInstance();
        mPresenter.attachView(this);
        mPresenter.getCategory();

        mPostsRecyclerAdapter = new PostsRecyclerAdapter();
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
