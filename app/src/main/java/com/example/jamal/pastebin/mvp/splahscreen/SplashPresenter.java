package com.example.jamal.pastebin.mvp.splahscreen;

import com.example.jamal.pastebin.ui.MainActivity;
import com.example.jamal.pastebin.data.global.DataManager;
import com.example.jamal.pastebin.mvp.global.MvpPresenter;
import com.example.jamal.pastebin.ui.auth.AuthActivity;

public class SplashPresenter extends MvpPresenter<SplashVIew> {

    private DataManager dataManager;

    public SplashPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void selectLogin() {
        if (dataManager.getToken() != null) {
            getView().selectLogin(MainActivity.class);
        } else {
            getView().selectLogin(AuthActivity.class);
        }
    }
}
