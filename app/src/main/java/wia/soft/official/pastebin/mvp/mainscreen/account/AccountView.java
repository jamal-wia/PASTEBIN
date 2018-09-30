package wia.soft.official.pastebin.mvp.mainscreen.account;

import wia.soft.official.pastebin.data.models.User;
import wia.soft.official.pastebin.mvp.global.MvpView;

public interface AccountView extends MvpView {

    void showInfoUser(User user);

    void editColorType(int color);

    void showMessage(String noNetwork);
}