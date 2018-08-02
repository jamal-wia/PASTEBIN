package com.example.jamal.pastebin.mvp.mainscreen.account;

import com.example.jamal.pastebin.data.models.User;
import com.example.jamal.pastebin.mvp.global.MvpView;

public interface AccountView extends MvpView{

    void showInfoUser(User user);

    void editColorType(int color);
}