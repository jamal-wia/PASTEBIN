package wia.soft.official.pastebin.mvp.mainscreen.listpaste.saved;

import java.util.List;

import wia.soft.official.pastebin.data.models.PasteRoom;
import wia.soft.official.pastebin.mvp.global.MvpView;

public interface SavedPasteView extends MvpView {

    void showListSaved(List<PasteRoom> pasteRooms);

    void showDialogWindow(PasteRoom pasteRoom);

    void updateRemoveRecycleView(List<PasteRoom> allPaste);
}