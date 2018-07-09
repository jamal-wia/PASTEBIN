package com.example.jamal.pastebin.mvp.mainscreen.listpaste;

import com.example.jamal.pastebin.data.global.DataManager;
import com.example.jamal.pastebin.data.models.PasteByUser;
import com.example.jamal.pastebin.mvp.global.MvpPresenter;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPasteByUserPresenter extends MvpPresenter<ListPasteByUserView> {

    private DataManager dataManager;
    private List<PasteByUser> pasteByUserList;

    public ListPasteByUserPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void showListPaste() {
        dataManager.getListPasteByUser(dataManager.getToken()).enqueue(new Callback<PasteByUser>() {
            @Override
            public void onResponse(Call<PasteByUser> call, Response<PasteByUser> response) {
                if (getView() != null) {
                    if (response.isSuccessful()) {
                        pasteByUserList = (List<PasteByUser>) response.body();
                    }
                }
            }

            @Override
            public void onFailure(Call<PasteByUser> call, Throwable t) {

            }
        });
    }
}