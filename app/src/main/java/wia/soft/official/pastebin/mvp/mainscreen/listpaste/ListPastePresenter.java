package wia.soft.official.pastebin.mvp.mainscreen.listpaste;

import wia.soft.official.pastebin.mvp.global.MvpPresenter;
import wia.soft.official.pastebin.ui.mainscreen.listpaste.saved.SavedPasteFragment;

public class ListPastePresenter extends MvpPresenter<ListPasteView> {

    public void startView() {
        getView().showFragment(new SavedPasteFragment());
    }
}