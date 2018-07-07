package com.example.jamal.pastebin.mvp.auth.login;

import com.example.jamal.pastebin.mvp.global.MvpView;

public interface LoginView extends MvpView {
    void showMessage(String message);

    void showEmptyLoginError();

    void showEmptyPasswordError();

    void navigateToMain();
}