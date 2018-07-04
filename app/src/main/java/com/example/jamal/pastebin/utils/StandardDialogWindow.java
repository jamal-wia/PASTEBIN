package com.example.jamal.pastebin.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;

public class StandardDialogWindow {

    private OnClickPositiveButtonListener onClickPositiveButtonListener;
    private OnClickNegativeButtonListener onClickNegativeButtonListener;

    public StandardDialogWindow(OnClickPositiveButtonListener onClickPositiveButtonListener,
                                OnClickNegativeButtonListener onClickNegativeButtonListener) {
        this.onClickPositiveButtonListener = onClickPositiveButtonListener;
        this.onClickNegativeButtonListener = onClickNegativeButtonListener;
    }

    public StandardDialogWindow(OnClickPositiveButtonListener onClickPositiveButtonListener) {
        this.onClickPositiveButtonListener = onClickPositiveButtonListener;
    }

    public StandardDialogWindow(OnClickNegativeButtonListener onClickNegativeButtonListener) {
        this.onClickNegativeButtonListener = onClickNegativeButtonListener;
    }

    public void withNegativeAndPositiveButoon(Context context, String title, String message,
                                              String titleForPositiveButton,
                                              String titleForNegativeButton) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(titleForPositiveButton, (dialog1, id) ->
                        onClickPositiveButtonListener.ClickPositive())
                .setNegativeButton(titleForNegativeButton, (dialog12, id) ->
                        onClickNegativeButtonListener.ClickNegative()).create();
        dialog.show();
    }

    public void withNegativeButton(Context context, String title, String message,
                                   String titleForNegativeButton) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(titleForNegativeButton, (dialog12, id) ->
                        onClickNegativeButtonListener.ClickNegative()).create();
        dialog.show();
    }

    public void withPositiveButton(Context context, String title, String message,
                                   String titleForPositiveButton) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(titleForPositiveButton, (dialog1, id) ->
                        onClickPositiveButtonListener.ClickPositive()).create();
        dialog.show();
    }


    public interface OnClickPositiveButtonListener {
        void ClickPositive();
    }

    public interface OnClickNegativeButtonListener {
        void ClickNegative();
    }
}
