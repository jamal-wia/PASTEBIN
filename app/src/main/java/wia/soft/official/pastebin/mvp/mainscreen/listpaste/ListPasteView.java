package wia.soft.official.pastebin.mvp.mainscreen.listpaste;

import android.support.v4.app.Fragment;

import wia.soft.official.pastebin.mvp.global.MvpView;

public interface ListPasteView extends MvpView {

    void showFragment(Fragment Fragment);
}