package com.example.jamal.pastebin.mvp.mainscreen.createpaste;

import com.example.jamal.pastebin.data.global.DataManager;
import com.example.jamal.pastebin.mvp.global.MvpPresenter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePastePresenter extends MvpPresenter<CreatePasteView> {

    private DataManager dataManager;

    public CreatePastePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void createButtonClick(String apiPasteName, String apiPasteFormat, int apiPastePrivate,
                                  String apiPasteExpireDate, String apiPasteCode) {

        dataManager.createPaste(apiPasteName, apiPasteFormat,
                apiPastePrivate, apiPasteExpireDate, apiPasteCode)

                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            String answer = null;
                            try {
                                answer = response.body().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            if (getView() != null) getView().showMessage(answer);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if(getView()!=null)getView().showMessage("Проверте соеденение с интернетом");
                    }
                });
    }
}