package com.example.jamal.pastebin.utils;

import android.content.Context;
import android.widget.Toast;

public class CommonUtils {

    private CommonUtils(){}

    public static void showToastShort(Context context, String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
    
    public static void showToastLong(Context context, String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
