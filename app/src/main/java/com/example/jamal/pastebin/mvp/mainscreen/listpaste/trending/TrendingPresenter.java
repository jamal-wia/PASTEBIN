package com.example.jamal.pastebin.mvp.mainscreen.listpaste.trending;

import com.example.jamal.pastebin.data.global.DataManager;
import com.example.jamal.pastebin.data.models.PasteNetwork;
import com.example.jamal.pastebin.data.models.PasteRoom;
import com.example.jamal.pastebin.mvp.global.MvpPresenter;
import com.example.jamal.pastebin.utils.Parse;

import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrendingPresenter extends MvpPresenter<TrendingView> {
    private DataManager dataManager;

    private Call<ResponseBody> rawTrendingPaste;

    public TrendingPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void showListTrendingPaste() {
        if (getView() != null) getView().showProgress(true);
        rawTrendingPaste = dataManager.getListTrendingPaste();
        rawTrendingPaste.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String answer = response.body().string();
                        if (getView() != null) {
                            getView().showListTrendingPaste(Parse.parsePaste(answer));
                            getView().showProgress(false);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void insertPaste(PasteRoom paste) {
        dataManager.insertPaste(paste);
    }

    public List<PasteRoom> getAllPaste() {
        return dataManager.getAllPaste();
    }

    public void showDialogWindow(PasteRoom pasteRoom) {
        getView().showDialogWindow(pasteRoom);
    }

    public void cancelRequest() {
        rawTrendingPaste.cancel();
    }
}