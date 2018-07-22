package com.example.jamal.pastebin.mvp.mainscreen.listpaste.global;

import com.example.jamal.pastebin.mvp.global.MvpPresenter;
import com.example.jamal.pastebin.ui.mainscreen.listpaste.byuser.ByUserFragment;

public class ListPastePresenter extends MvpPresenter<ListPasteView>{

    public void startView() {
        getView().showFragment(new ByUserFragment());
    }
}