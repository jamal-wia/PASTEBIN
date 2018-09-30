package wia.soft.official.pastebin.mvp.mainscreen.account;

import com.example.jamal.pastebin.R;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wia.soft.official.pastebin.data.global.DataManager;
import wia.soft.official.pastebin.data.models.User;
import wia.soft.official.pastebin.mvp.global.MvpPresenter;
import wia.soft.official.pastebin.utils.Parse;

public class AccountPresenter extends MvpPresenter<AccountView> {
    private DataManager dataManager;

    private Call<ResponseBody> infoUserCall;

    public AccountPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void showInfoUser() {
        infoUserCall = dataManager.infoUser();
        infoUserCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && getView() != null) {
                    String answer;
                    User user = null;

                    try {
                        answer = response.body().string();
                        user = Parse.parseAnswer(answer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (user.getAccountType().equals("1")) {
                        user.setAccountType("Pro");
                        getView().editColorType(R.color.colorRedTypeAccount);
                    } else {
                        user.setAccountType("Free");
                        getView().editColorType(R.color.colorPrimaryDark);
                    }

                    if (getView() != null) getView().showInfoUser(user);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (getView() != null) getView().showMessage("no Network");
            }
        });
    }

    public void cancelRequest() {
        if (infoUserCall != null) infoUserCall.cancel();
    }
}