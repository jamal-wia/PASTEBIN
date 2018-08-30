package com.example.jamal.pastebin.mvp.mainscreen.main;

import android.support.v4.app.Fragment;

import com.example.jamal.pastebin.mvp.global.MvpView;
import com.example.jamal.pastebin.ui.mainscreen.listpaste.ListPasteFragment;

public interface MainView extends MvpView {
    void startFragment(Fragment fragment);

    void startView(Fragment fragment);

}