package com.example.jamal.pastebin.data.local;

import android.content.SharedPreferences;

public class PreferencesHelper {

    private static final String PREF_TOKEN ="TOKEN";

    private SharedPreferences sharedPreferences;

    public PreferencesHelper(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void saveToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_TOKEN,token);
        editor.apply();
    }

    public String getToken(){
        return sharedPreferences.getString(PREF_TOKEN,null);
    }
}