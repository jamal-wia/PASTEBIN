package com.example.jamal.pastebin.mvp.mainscreen.listpaste;

import com.example.jamal.pastebin.mvp.global.MvpPresenter;
import com.example.jamal.pastebin.ui.mainscreen.account.listpaste.MyPasteFragment;
import com.example.jamal.pastebin.ui.mainscreen.listpaste.saved.SavedPasteFragment;

public class ListPastePresenter extends MvpPresenter<ListPasteView>{

    public void startView() {
        getView().showFragment(new SavedPasteFragment());
    }
}