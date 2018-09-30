package wia.soft.official.pastebin.ui.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.jamal.pastebin.R;

import wia.soft.official.pastebin.App;
import wia.soft.official.pastebin.mvp.splahscreen.SplashPresenter;
import wia.soft.official.pastebin.mvp.splahscreen.SplashVIew;

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
        }, 1000);
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