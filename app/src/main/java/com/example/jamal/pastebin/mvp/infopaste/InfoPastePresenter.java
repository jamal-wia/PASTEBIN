package com.example.jamal.pastebin.mvp.infopaste;

import com.example.jamal.pastebin.data.global.DataManager;
import com.example.jamal.pastebin.mvp.global.MvpPresenter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoPastePresenter extends MvpPresenter<InfoPasteView> {

    private DataManager dataManager;

    public InfoPastePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void showRawPaste(String apiPasteKey) {
        dataManager.getRawPaste(apiPasteKey).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String code = response.body().string();
                        if (code.equals("Bad API request," +
                                " invalid permission to view this paste or invalid api_paste_key")) {
                            getView().showTrendingPaste(apiPasteKey);
                        } else getView().showPaste(code);
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

    public void showRawTrendingPaste(String apiPasteKey) {
        dataManager.getRawTrendingPaste(apiPasteKey).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String code = response.body().string();
                        getView().showPaste(code);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                String s = "";
            }
        });
    }
}