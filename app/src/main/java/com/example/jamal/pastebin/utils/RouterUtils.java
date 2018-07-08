package com.example.jamal.pastebin.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class RouterUtils {

    public static void showFragmentInStack(int container, Fragment fragment,
                                           FragmentActivity fragmentActivity) {
        fragmentActivity.getSupportFragmentManager().beginTransaction()
                .addToBackStack(null).replace(container, fragment).commit();
    }

    public static void showFragment(int container, Fragment fragment,
                                    FragmentActivity fragmentActivity) {
        fragmentActivity.getSupportFragmentManager().beginTransaction()
                .replace(container, fragment).commit();
    }

    public void showActivity(Context context,Class<?> C){
        context.startActivity(new Intent(context,C));
    }
}