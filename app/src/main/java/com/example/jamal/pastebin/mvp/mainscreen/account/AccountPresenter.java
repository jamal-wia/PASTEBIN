package com.example.jamal.pastebin.mvp.mainscreen.account;

import com.example.jamal.pastebin.R;
import com.example.jamal.pastebin.data.global.DataManager;
import com.example.jamal.pastebin.data.models.User;
import com.example.jamal.pastebin.mvp.global.MvpPresenter;

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
                        user = parseAnswer(answer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    getView().showInfoUser(user);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private User parseAnswer(String answer) {
        User user;

        Reader reader = new StringReader(answer);
        Persister serializer = new Persister();

        try {
            user = serializer.read(User.class, reader, false);
        } catch (Exception e) {
            e.printStackTrace();
            user = parseWrongAnswer(answer);
        }

        if (user.getAccountType().equals("1")) {
            user.setAccountType("Pro");
            getView().editColorType(R.color.colorRedTypeAccount);
        } else {
            user.setAccountType("Free");
            getView().editColorType(R.color.colorPrimaryDark);
        }

        return user;
    }

    private User parseWrongAnswer(String wrongAnswer) {
        String answer = "";
        String regexWebsite = "</user_website>";
        String regexLocation = "</user_location>";

        String[] dividedAnswer = new String[3];
        dividedAnswer[0] = wrongAnswer.split(regexWebsite)[0];
        dividedAnswer[1] = wrongAnswer.split(regexWebsite)[1].split(regexLocation)[0];
        dividedAnswer[2] = wrongAnswer.split(regexLocation)[1];

        char[] charsDividedAnswer0 = dividedAnswer[0].toCharArray();
        String lastCharAnswer0 = String.valueOf(charsDividedAnswer0[dividedAnswer[0].length() - 1]);

        if (lastCharAnswer0.equals(">")) {
            answer += dividedAnswer[0] + "unknown" + regexWebsite + dividedAnswer[1];
        } else {
            answer += dividedAnswer[0] + dividedAnswer[1];
        }

        char[] charsDividedAnswer1 = dividedAnswer[1].toCharArray();
        String lastCharAnswer1 = String.valueOf(charsDividedAnswer1[dividedAnswer[1].length() - 1]);
        String s = "";
        if (lastCharAnswer1.equals(">")) {
            answer += "unknown" + regexLocation + dividedAnswer[2];
        } else {
            answer += dividedAnswer[2];
        }

        return parseAnswer(answer);
    }
}