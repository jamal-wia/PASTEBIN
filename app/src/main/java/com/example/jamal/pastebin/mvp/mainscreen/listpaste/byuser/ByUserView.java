package com.example.jamal.pastebin.mvp.mainscreen.listpaste.byuser;

import com.example.jamal.pastebin.data.models.PasteByUser;
import com.example.jamal.pastebin.mvp.global.MvpView;

import java.util.List;

public interface ByUserView extends MvpView {

    void showListPaste(List<PasteByUser> pasteByUserList);
}