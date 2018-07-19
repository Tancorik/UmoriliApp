package com.example.tancorik.umoriliapp.dagger.module;

import android.support.annotation.NonNull;

import com.example.tancorik.umoriliapp.data.RemotePostService;
import com.example.tancorik.umoriliapp.domain.IRemotePostService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ServicesModule {

    @Provides
    @NonNull
    @Singleton
    public IRemotePostService provideRemotePostService() {
        return new RemotePostService();
    }
}
