package com.example.jamal.pastebin.mvp.mainscreen.account;

import com.example.jamal.pastebin.R;
import com.example.jamal.pastebin.data.global.DataManager;
import com.example.jamal.pastebin.data.models.User;
import com.example.jamal.pastebin.mvp.global.MvpPresenter;
import com.example.jamal.pastebin.utils.Parse;

import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountPresenter extends MvpPresenter<AccountView> {

    private DataManager dataManager;

    public AccountPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void showInfoUser() {
        dataManager.infoUser().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
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

                    getView().showInfoUser(user);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}