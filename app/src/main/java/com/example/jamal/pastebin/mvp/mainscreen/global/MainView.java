package com.example.jamal.pastebin.mvp.mainscreen.global;

import android.support.v4.app.Fragment;

import com.example.jamal.pastebin.mvp.global.MvpView;

public interface MainView extends MvpView {
    void startFragment(Fragment fragment);
}