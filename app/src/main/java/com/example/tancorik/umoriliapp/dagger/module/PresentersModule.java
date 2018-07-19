package com.example.tancorik.umoriliapp.dagger.module;

import android.support.annotation.NonNull;

import com.example.tancorik.umoriliapp.presentation.presenter.MainScreenPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresentersModule {

    @Provides
    @NonNull
    @Singleton
    public MainScreenPresenter provideMainScreenPresenter() {
        return new MainScreenPresenter();
    }
}
