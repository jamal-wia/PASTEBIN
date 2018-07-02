package com.example.jamal.pastebin.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.jamal.pastebin.R;
import com.example.jamal.pastebin.entry.fragments.InputMailFragment;
import com.example.jamal.pastebin.entry.fragments.LoginFragment;
import com.example.jamal.pastebin.entry.fragments.NameAndPasswordFragment;

public class FragmentUtils {

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
}