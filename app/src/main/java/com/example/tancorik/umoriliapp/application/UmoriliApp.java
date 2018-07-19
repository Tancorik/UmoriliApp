package com.example.tancorik.umoriliapp.application;

import android.app.Application;

import com.example.tancorik.umoriliapp.dagger.component.AppComponent;
import com.example.tancorik.umoriliapp.dagger.component.DaggerAppComponent;
import com.example.tancorik.umoriliapp.dagger.module.ServicesModule;

public class UmoriliApp extends Application {

    private static AppComponent component;
    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .servicesModule(new ServicesModule())
                .build();
    }
}
