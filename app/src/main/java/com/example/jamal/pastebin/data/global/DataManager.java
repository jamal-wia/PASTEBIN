package com.example.jamal.pastebin.data.global;

import com.example.jamal.pastebin.data.local.PreferencesHelper;
import com.example.jamal.pastebin.data.models.PasteByUser;
import com.example.jamal.pastebin.data.network.PastebinServise;
import com.example.jamal.pastebin.utils.Constants;

import okhttp3.ResponseBody;
import retrofit2.Call;

import static com.example.jamal.pastebin.utils.Constants.DEV_KEY;

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

    public Call<PasteByUser> getListPasteByUser(String apiUserKey) {
        return pastebinServise.getListPasteByUser(DEV_KEY, apiUserKey, 100, "list");
    }

    public void saveToken(String token) {
        preferencesHelper.saveToken(token);
    }

    public String getToken() {
        return preferencesHelper.getToken();
    }
}