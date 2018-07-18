package com.example.tancorik.umoriliapp.presentation.ui;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.tancorik.umoriliapp.R;

public class ClipboardDialog extends DialogFragment {

    public static final String TAG = "clipboardDialogTag";
    private static final String INSTANCE_KEY = "clipboardInstanceKey";
    private static final String COPY_TO_CLIPBOARD_MESSAGE = "Хотите копировать данный текст в буффер?";

    public static ClipboardDialog newInstance(String string) {
        ClipboardDialog clipboardDialog = new ClipboardDialog();
        Bundle arguments = new Bundle();
        arguments.putString(INSTANCE_KEY, string);
        clipboardDialog.setArguments(arguments);
        return clipboardDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String string = getArguments().getString(INSTANCE_KEY);
        builder.setMessage(COPY_TO_CLIPBOARD_MESSAGE)
                .setPositiveButton(R.string.ok_text, (dialogInterface, i) -> {
                    copyToClipboard(string);
                })
                .setNegativeButton(R.string.cancel_text, (dialogInterface, i) -> {

                });
        return builder.create();
    }

    private void copyToClipboard(String string) {
        ClipboardManager manager = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData data = ClipData.newPlainText(ClipDescription.MIMETYPE_TEXT_PLAIN, string);
        manager.setPrimaryClip(data);
    }
}
