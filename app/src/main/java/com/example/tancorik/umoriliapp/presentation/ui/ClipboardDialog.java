package com.example.tancorik.umoriliapp.presentation.ui;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
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

public class ClipboardDialog extends DialogFragment {

    public static final String TAG = "clipboardDialogTag";
    private static final String COPY_TO_CLIPBOARD_MESSAGE = "Хотите копировать данный текст в буффер?";

    @Inject
    MainScreenPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UmoriliApp.getComponent().inject(this);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        builder.setMessage(COPY_TO_CLIPBOARD_MESSAGE)
                .setPositiveButton(R.string.ok_text, (dialogInterface, i) ->
                        copyToClipboard(mPresenter.getSelectedPost()))
                .setNegativeButton(R.string.cancel_text, (dialogInterface, i) -> {});
        return builder.create();
    }

    private void copyToClipboard(String string) {
        Context context = getContext();
        if (context == null)
            return;
        ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData data = ClipData.newPlainText(ClipDescription.MIMETYPE_TEXT_PLAIN, string);
        if (manager != null) {
            manager.setPrimaryClip(data);
        }
    }
}
