package com.example.jamal.pastebin.ui.auth.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.jamal.pastebin.App;
import com.example.jamal.pastebin.R;
import com.example.jamal.pastebin.mvp.auth.login.SplashPresenter;
import com.example.jamal.pastebin.mvp.auth.login.SplashVIew;

public class SplashActivity extends AppCompatActivity implements SplashVIew {

    private SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splesh);
        splashPresenter = new SplashPresenter(App.getDataManager());
        splashPresenter.attachView(this);
        splashPresenter.selectLogin();
    }

    @Override
    public void selectLogin(Class<?> C) {
        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, C));
            finish();
        }, 500);
    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    protected void onDestroy() {
        splashPresenter.detachView();
        super.onDestroy();
    }
}