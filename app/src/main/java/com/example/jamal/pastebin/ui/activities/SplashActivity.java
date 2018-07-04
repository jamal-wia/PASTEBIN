package com.example.jamal.pastebin.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.jamal.pastebin.App;
import com.example.jamal.pastebin.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splesh);

        new Handler().postDelayed(() -> {
            if (App.getPreferencesHelper().getToken() != null) {
                showMain();
            } else {
                showAuth();
            }
        }, 500);
    }

    private void showAuth() {
        startActivity(new Intent(this, AuthActivity.class));
        finish();
    }

    private void showMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
