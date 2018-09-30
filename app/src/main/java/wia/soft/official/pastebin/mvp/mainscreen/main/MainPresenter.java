package wia.soft.official.pastebin.mvp.mainscreen.main;

import com.example.jamal.pastebin.R;

import wia.soft.official.pastebin.mvp.global.MvpPresenter;
import wia.soft.official.pastebin.ui.mainscreen.account.AccountFragment;
import wia.soft.official.pastebin.ui.mainscreen.createpaste.CreatePasteFragment;
import wia.soft.official.pastebin.ui.mainscreen.listpaste.ListPasteFragment;

public class MainPresenter extends MvpPresenter<MainView> {

    public void onBottomNavigationSelectClick(int itemId) {
        switch (itemId) {
            case R.id.item_navigationBar_paste:
                getView().startFragment(new ListPasteFragment());
                break;
            case R.id.item_navigationBar_addPaste:
                getView().startFragment(new CreatePasteFragment());
                break;
            case R.id.item_navigationBar_account:
                getView().startFragment(new AccountFragment());
                break;
        }
    }

    public void startView() {
        getView().startView(new ListPasteFragment());
    }
}