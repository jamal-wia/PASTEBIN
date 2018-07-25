package com.example.jamal.pastebin.data.global;

import com.example.jamal.pastebin.data.local.PreferencesHelper;
import com.example.jamal.pastebin.data.network.PastebinServise;

import okhttp3.ResponseBody;
import retrofit2.Call;

import static com.example.jamal.pastebin.utils.Constants.DEV_KEY;
import static com.example.jamal.pastebin.utils.Constants.PASTE_PARAM_LIST;
import static com.example.jamal.pastebin.utils.Constants.PASTE_PARAM_TRENDS;

public class DataManager {

    private PastebinServise pastebinServise;
    private PreferencesHelper preferencesHelper;

    public DataManager(PastebinServise pastebinServise, PreferencesHelper preferencesHelper) {
        this.pastebinServise = pastebinServise;
        this.preferencesHelper = preferencesHelper;
    }

    public Call<ResponseBody> login(String login, String password) {
        return pastebinServise.login(DEV_KEY, login, password);
    }

    public Call<ResponseBody> getListPasteByUser(String apiUserKey) {
        return pastebinServise.getListPasteByUser(DEV_KEY, apiUserKey, 100,
                PASTE_PARAM_LIST);
    }

    public Call<ResponseBody> getListTrendingPaste() {
        return pastebinServise.getListTrendingPaste(DEV_KEY, PASTE_PARAM_TRENDS);
    }

    public void setToken(String token) {
        preferencesHelper.setToken(token);
    }

    public String getToken() {
        return preferencesHelper.getToken();
    }
}