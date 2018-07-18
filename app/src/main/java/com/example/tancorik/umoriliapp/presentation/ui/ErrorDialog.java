package com.example.tancorik.umoriliapp.presentation.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.tancorik.umoriliapp.R;
import com.example.tancorik.umoriliapp.presentation.presenter.MainScreenPresenter;

import java.util.Objects;

public class ErrorDialog extends DialogFragment {

    public static final String TAG = "errorDialog";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        String errorMessage = MainScreenPresenter.getInstance().getErrorMessage();
        if (errorMessage == null) {
            errorMessage = "Ошибка!";
        }
        dialog.setMessage(errorMessage)
                .setPositiveButton(R.string.ok_text, (dialogInterface, i) -> {
                });

        return dialog.create();
    }
}
