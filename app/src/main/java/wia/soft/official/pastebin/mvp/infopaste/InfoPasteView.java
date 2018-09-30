package wia.soft.official.pastebin.mvp.infopaste;

import wia.soft.official.pastebin.mvp.global.MvpView;

public interface InfoPasteView extends MvpView {

    void showPaste(String code);

    void showTrendingPaste(String apiPasteKey);
}