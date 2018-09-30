package wia.soft.official.pastebin.mvp.mainscreen.account.listpaste;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wia.soft.official.pastebin.data.global.DataManager;
import wia.soft.official.pastebin.data.models.PasteRoom;
import wia.soft.official.pastebin.mvp.global.MvpPresenter;
import wia.soft.official.pastebin.utils.Parse;

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