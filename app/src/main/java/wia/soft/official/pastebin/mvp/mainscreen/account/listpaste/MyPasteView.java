package wia.soft.official.pastebin.mvp.mainscreen.account.listpaste;


import java.util.List;

import wia.soft.official.pastebin.data.models.PasteNetwork;
import wia.soft.official.pastebin.data.models.PasteRoom;
import wia.soft.official.pastebin.mvp.global.MvpView;

public interface MyPasteView extends MvpView {

    void showListPaste(List<PasteNetwork> pasteNetworkList);

    void showDialogWindow(PasteRoom paste);

    void showMessage(String message);

    void removePaste(PasteRoom pasteRoom);
}