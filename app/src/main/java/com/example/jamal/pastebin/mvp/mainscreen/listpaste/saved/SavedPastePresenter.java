package com.example.jamal.pastebin.mvp.mainscreen.listpaste.saved;

import com.example.jamal.pastebin.data.global.DataManager;
import com.example.jamal.pastebin.mvp.global.MvpPresenter;

public class SavedPastePresenter extends MvpPresenter<SavedPasteView> {

    private DataManager dataManager;

    public SavedPastePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void showListSaved(){

    }
}