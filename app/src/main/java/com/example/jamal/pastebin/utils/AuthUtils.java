package com.example.jamal.pastebin.utils;

import android.text.TextUtils;

public class AuthUtils {
    public static boolean isEmptyFields(String field) {
        return TextUtils.isEmpty(field) || field.trim().length() < 1;
    }
}
