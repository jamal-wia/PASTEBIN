package com.example.jamal.pastebin.mvp.mainscreen.listpaste;

import android.support.v4.app.Fragment;

import com.example.jamal.pastebin.mvp.global.MvpView;

public interface ListPasteView extends MvpView {

    void showFragment(Fragment Fragment);
}