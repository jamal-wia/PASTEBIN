package com.example.jamal.pastebin.data.global;

import com.example.jamal.pastebin.data.local.PreferencesHelper;
import com.example.jamal.pastebin.data.network.PastebinServise;
import com.example.jamal.pastebin.utils.Constants;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class DataManager {

    private PastebinServise pastebinServise;
    private PreferencesHelper preferencesHelper;

    public DataManager(PastebinServise pastebinServise, PreferencesHelper preferencesHelper) {
        this.pastebinServise = pastebinServise;
        this.preferencesHelper = preferencesHelper;
    }

    public Call<ResponseBody> login(String login, String password) {
        return pastebinServise.login(Constants.DEV_KEY, login, password);
    }

    public void saveToken(String token){
        preferencesHelper.saveToken(token);
    }

    public String getToken(){
        return preferencesHelper.getToken();
    }
}