package com.example.jamal.pastebin.mvp.mainscreen.global;

import com.example.jamal.pastebin.R;
import com.example.jamal.pastebin.mvp.global.MvpPresenter;
import com.example.jamal.pastebin.ui.mainscreen.account.AccountFragment;
import com.example.jamal.pastebin.ui.mainscreen.createpaste.CreatePasteFragment;
import com.example.jamal.pastebin.ui.mainscreen.listpaste.global.ListPasteFragment;

public class MainPresenter extends MvpPresenter<MainView> {

    public void onBottomNavigationSelectClick(int itemId) {
        switch (itemId) {
            case R.id.item_navigationBar_paste:
                getView().startFragment(new ListPasteFragment());
                break;
            case R.id.item_navigationBar_addPaste:
                getView().startFragment(new CreatePasteFragment());
                break;
            case R.id.item_navigationBar_message:
//                getView().startFragment(new AccountFragment());
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