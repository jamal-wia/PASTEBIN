package wia.soft.official.pastebin.mvp.mainscreen.createpaste;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wia.soft.official.pastebin.data.global.DataManager;
import wia.soft.official.pastebin.mvp.global.MvpPresenter;

public class CreatePastePresenter extends MvpPresenter<CreatePasteView> {
    private DataManager dataManager;

    private Call<ResponseBody> createPasteCall;

    public CreatePastePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void createButtonClick(String apiPasteName, String apiPasteFormat, int apiPastePrivate,
                                  String apiPasteExpireDate, String apiPasteCode) {

        createPasteCall = dataManager.createPaste(apiPasteName, apiPasteFormat,
                apiPastePrivate, apiPasteExpireDate, apiPasteCode);
        createPasteCall.enqueue(new Callback<ResponseBody>() {
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

            }
        });
    }

    public void cancelRequest() {
        if (createPasteCall != null) createPasteCall.cancel();
    }
}