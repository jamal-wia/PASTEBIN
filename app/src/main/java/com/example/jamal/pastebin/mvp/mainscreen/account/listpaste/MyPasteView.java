package com.example.jamal.pastebin.mvp.mainscreen.account.listpaste;

import com.example.jamal.pastebin.data.models.PasteNetwork;
import com.example.jamal.pastebin.mvp.global.MvpView;

import java.util.List;

public interface MyPasteView extends MvpView {

    void showListPaste(List<PasteNetwork> pasteNetworkList);
}