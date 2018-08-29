package com.example.jamal.pastebin.mvp.mainscreen.listpaste.saved;

import com.example.jamal.pastebin.data.global.DataManager;
import com.example.jamal.pastebin.data.models.PasteRoom;
import com.example.jamal.pastebin.mvp.global.MvpPresenter;

public class SavedPastePresenter extends MvpPresenter<SavedPasteView> {

    private DataManager dataManager;

    public SavedPastePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void showListSaved() {
        if (getView() != null) getView().showListSaved(dataManager.getAllPaste());
    }

    public void itemLongClick(PasteRoom pasteRoom) {
        if (getView() != null) getView().showDialogWindow(pasteRoom);
    }

    public void delete(PasteRoom pasteRoom) {
        dataManager.deletePaste(pasteRoom);
    }

    public void updateRecycleView() {
        if (getView() != null) getView().updateRemoveRecycleView(dataManager.getAllPaste());
    }
}