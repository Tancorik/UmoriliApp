package com.example.tancorik.umoriliapp.dagger.component;

import com.example.tancorik.umoriliapp.dagger.module.PresentersModule;
import com.example.tancorik.umoriliapp.dagger.module.ServicesModule;
import com.example.tancorik.umoriliapp.presentation.presenter.MainScreenPresenter;
import com.example.tancorik.umoriliapp.presentation.ui.ClipboardDialog;
import com.example.tancorik.umoriliapp.presentation.ui.ErrorDialog;
import com.example.tancorik.umoriliapp.presentation.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {ServicesModule.class, PresentersModule.class})
@Singleton
public interface AppComponent {

    void inject(MainScreenPresenter mainScreenPresenter);
    void inject(MainActivity mainActivity);
    void inject(ErrorDialog errorDialog);
    void inject(ClipboardDialog clipboardDialog);
}
