package com.example.jamal.pastebin.mvp.mainscreen.listpaste.global;

import android.support.v4.app.Fragment;

import com.example.jamal.pastebin.mvp.global.MvpView;

public interface ListPasteView extends MvpView {

    void showFragment(Fragment Fragment);
}