package com.example.jamal.pastebin;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.jamal.pastebin.data.global.DataManager;
import com.example.jamal.pastebin.data.local.Database;
import com.example.jamal.pastebin.data.local.PreferencesHelper;
import com.example.jamal.pastebin.data.models.PasteRoom;
import com.example.jamal.pastebin.data.network.PastebinServise;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BASIC;

public class App extends Application {

    public static final String BASE_URL = "https://pastebin.com/api/";

    private static DataManager dataManager;

    @Override
    public void onCreate() {
        super.onCreate();

        // HttpLoggingInterceptor
        HttpLoggingInterceptor httpLoggingInterceptor= new HttpLoggingInterceptor()
                .setLevel(BASIC);

        // OkHttpClient
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        // Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(SimpleXmlConverterFactory./*createNonStrict()*/create())
                .build();

        // PastebinServise
        PastebinServise pastebinServise = retrofit.create(PastebinServise.class);

        // PreferencesHelper
        PreferencesHelper preferencesHelper =
                new PreferencesHelper(getSharedPreferences( "SharedPrefs",Context.MODE_PRIVATE));

        Database database = Room.databaseBuilder(this, Database.class,"database").build();

        dataManager = new DataManager(pastebinServise,preferencesHelper,database);
    }

    public static DataManager getDataManager() {
        return dataManager;
    }
}