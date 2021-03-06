package wia.soft.official.pastebin.mvp.infopaste;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wia.soft.official.pastebin.data.global.DataManager;
import wia.soft.official.pastebin.mvp.global.MvpPresenter;

public class InfoPastePresenter extends MvpPresenter<InfoPasteView> {
    private DataManager dataManager;

    private Call<ResponseBody> getRawPasteCall;

    public InfoPastePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void showRawPaste(String apiPasteKey) {
        getRawPasteCall = dataManager.getRawPaste(apiPasteKey);
        getRawPasteCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String code = response.body().string();
                        if (code.equals("Bad API request," +
                                " invalid permission to view this paste or invalid api_paste_key")) {
                            if (getView() != null) getView().showTrendingPaste(apiPasteKey);
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

    public void cancelRequest() {
        if (getRawPasteCall != null) getRawPasteCall.cancel();
    }
}