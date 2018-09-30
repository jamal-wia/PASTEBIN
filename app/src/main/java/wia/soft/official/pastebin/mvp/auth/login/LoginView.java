package wia.soft.official.pastebin.mvp.auth.login;

import wia.soft.official.pastebin.mvp.global.MvpView;

public interface LoginView extends MvpView {
    void showMessage(String message);

    void showEmptyLoginError();

    void showEmptyPasswordError();

    void navigateToMain();
}