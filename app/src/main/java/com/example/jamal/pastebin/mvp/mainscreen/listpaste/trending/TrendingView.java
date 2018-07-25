package com.example.jamal.pastebin.mvp.mainscreen.listpaste.trending;

import com.example.jamal.pastebin.data.models.Paste;
import com.example.jamal.pastebin.mvp.global.MvpView;

import java.util.List;

public interface TrendingView extends MvpView {

    void showListTrendingPaste(List<Paste> pastes);
}