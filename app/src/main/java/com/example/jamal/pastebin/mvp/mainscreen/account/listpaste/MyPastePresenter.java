package com.example.jamal.pastebin.mvp.mainscreen.account.listpaste;

import com.example.jamal.pastebin.data.global.DataManager;
import com.example.jamal.pastebin.data.models.Paste;
import com.example.jamal.pastebin.mvp.global.MvpPresenter;

import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPastePresenter extends MvpPresenter<MyPasteView> {

    private DataManager dataManager;

    public MyPastePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void showListPaste() {
        dataManager.getListPasteByUser(dataManager.getToken()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String answer = response.body().string();
                    String regex = "</paste>";
                    String[] sArr = answer.split(regex);

                    for (int i = 0; i < sArr.length; i++) {
                        sArr[i] += regex;
                    }

                    List<String> pasteList = new ArrayList<>();

                    for (int i = 0; i < sArr.length; i++) {
                        pasteList.add(sArr[i]);
                    }

                    pasteList.remove(pasteList.size() - 1);

                    List<Paste> pasteByUserList = new ArrayList<>();

                    for (int i = 0; i < pasteList.size(); i++) {
                        Reader reader = new StringReader(pasteList.get(i));
                        Persister serializer = new Persister();
                        try {
                            pasteByUserList.add(serializer.read(Paste.class, reader, false));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (getView() != null) getView().showListPaste(pasteByUserList);
                    if (getView() != null) getView().showProgress(false);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}