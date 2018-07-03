package com.example.jamal.pastebin;

import android.app.Application;
import android.content.Context;

import com.example.jamal.pastebin.data.local.PreferencesHelper;
import com.example.jamal.pastebin.data.network.PastebinServise;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BASIC;

public class App extends Application {

    public static final String BASE_URL = "https://pastebin.com/api/";

    private static PastebinServise pastebinServise;
    private static PreferencesHelper preferencesHelper;

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new HttpLoggingInterceptor().setLevel(BASIC))
                        .build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        pastebinServise = retrofit.create(PastebinServise.class);

        preferencesHelper = new PreferencesHelper(getSharedPreferences("SharedPrefs", Context.MODE_PRIVATE));
    }

    public static PastebinServise getPastebinServise() {
        return pastebinServise;
    }

    public static PreferencesHelper getPreferencesHelper() {
        return preferencesHelper;
    }
}