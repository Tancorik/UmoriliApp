package com.example.tancorik.umoriliapp.presentation.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.tancorik.umoriliapp.R;
import com.example.tancorik.umoriliapp.application.UmoriliApp;
import com.example.tancorik.umoriliapp.presentation.presenter.MainScreenPresenter;

import java.util.Objects;

import javax.inject.Inject;

public class ErrorDialog extends DialogFragment {

    public static final String TAG = "errorDialog";
    @Inject MainScreenPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UmoriliApp.getComponent().inject(this);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        String errorMessage = mPresenter.getErrorMessage();
        if (errorMessage == null) {
            errorMessage = "Ошибка!";
        }
        dialog.setMessage(errorMessage)
                .setPositiveButton(R.string.ok_text, (dialogInterface, i) -> {
                });

        return dialog.create();
    }
}
