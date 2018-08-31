package com.example.jamal.pastebin.mvp.mainscreen.account.listpaste;

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

public class MyPastePresenter extends MvpPresenter<MyPasteView> {
    private DataManager dataManager;

    private Call<ResponseBody> listPasteByUser;

    public MyPastePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void showListPaste() {
        listPasteByUser = dataManager.getListPasteByUser(dataManager.getToken());
        listPasteByUser.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String answer = response.body().string();
                        if (getView() != null) {
                            getView().showListPaste(Parse.parsePaste(answer));
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

    public void showDialogWindow(PasteRoom paste) {
        getView().showDialogWindow(paste);
    }

    public void insertPaste(PasteRoom paste) {
        dataManager.getRawTrendingPaste(paste.getKey()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String code = response.body().string();
                        paste.setCode(code);
                        dataManager.insertPaste(paste);
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

    public void exit() {
        dataManager.exit();
    }

    public void cancelRequest() {
        if (listPasteByUser != null) listPasteByUser.cancel();
    }

    public void removePaste(PasteRoom pasteRoom) {
        dataManager.removePaste(pasteRoom.getKey()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && getView() != null) {
                    try {
                        getView().removePaste(pasteRoom);
                        getView().showMessage(response.body().string());
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
}