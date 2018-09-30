package wia.soft.official.pastebin.mvp.mainscreen.main;

import android.support.v4.app.Fragment;

import wia.soft.official.pastebin.mvp.global.MvpView;

public interface MainView extends MvpView {
    void startFragment(Fragment fragment);

    void startView(Fragment fragment);

}