package com.example.jamal.pastebin.mvp.mainscreen.listpaste.saved;

import com.example.jamal.pastebin.data.models.PasteRoom;
import com.example.jamal.pastebin.mvp.global.MvpView;

import java.util.List;

public interface SavedPasteView extends MvpView{

    void showListSaved(List<PasteRoom> pasteRooms);

    void showDialogWindow(PasteRoom pasteRoom);

    void updateRemoveRecycleView(List<PasteRoom> allPaste);
}