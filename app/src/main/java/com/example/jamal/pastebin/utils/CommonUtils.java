package com.example.jamal.pastebin.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public class CommonUtils {

    private CommonUtils() {
    }

    public static void showToastShort(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showToastLong(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showStandartDialogWindowsWithPositiveButton(Context context, String title, String message,
                                                                   String titleForPositiveButton,
                                                                   OnClickPositiveButtonListener onClickPositiveButtonListener) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(titleForPositiveButton, (dialog1, id) ->
                        onClickPositiveButtonListener.ClickPositive()).create();
        dialog.show();
    }

    public static void showStandartDialogWindowsWithNegativeButton(Context context, String title, String message,
                                                                   String titleForNegativeButton,
                                                                   OnClickNegativeButtonListener onClickNegativeButtonListener) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(titleForNegativeButton, (dialog12, id) ->
                        onClickNegativeButtonListener.ClickNegative()).create();
        dialog.show();
    }

    public static void showStandartDialogWindowsWithSecondButton(Context context, String title, String message,
                                                                 String titleForPositiveButton,
                                                                 String titleForNegativeButton,
                                                                 OnClickPositiveButtonListener onClickPositiveButtonListener,
                                                                 OnClickNegativeButtonListener onClickNegativeButtonListener){
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(titleForPositiveButton, (dialog1, id) ->
                        onClickPositiveButtonListener.ClickPositive())
                .setNegativeButton(titleForNegativeButton, (dialog12, id) ->
                        onClickNegativeButtonListener.ClickNegative()).create();
        dialog.show();

    }

    public interface OnClickPositiveButtonListener {
        void ClickPositive();
    }

    public interface OnClickNegativeButtonListener {
        void ClickNegative();
    }
}