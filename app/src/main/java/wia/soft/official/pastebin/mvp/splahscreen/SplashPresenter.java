package wia.soft.official.pastebin.mvp.splahscreen;


import wia.soft.official.pastebin.data.global.DataManager;
import wia.soft.official.pastebin.mvp.global.MvpPresenter;
import wia.soft.official.pastebin.ui.auth.AuthActivity;
import wia.soft.official.pastebin.ui.mainscreen.main.MainActivity;

public class SplashPresenter extends MvpPresenter<SplashVIew> {

    private DataManager dataManager;

    public SplashPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void selectLogin() {
        if (dataManager.getToken() != null ) {
            getView().selectLogin(MainActivity.class);
        } else {
            getView().selectLogin(AuthActivity.class);
        }
    }
}