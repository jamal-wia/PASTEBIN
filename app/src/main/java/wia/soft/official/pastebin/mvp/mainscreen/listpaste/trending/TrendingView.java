package wia.soft.official.pastebin.mvp.mainscreen.listpaste.trending;

import java.util.List;

import wia.soft.official.pastebin.data.models.PasteNetwork;
import wia.soft.official.pastebin.data.models.PasteRoom;
import wia.soft.official.pastebin.mvp.global.MvpView;

public interface TrendingView extends MvpView {

    void showListTrendingPaste(List<PasteNetwork> pasteNetworks);

    void showDialogWindow(PasteRoom pasteRoom);
}