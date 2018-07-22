package com.example.jamal.pastebin.ui.mainscreen.global;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.jamal.pastebin.R;
import com.example.jamal.pastebin.mvp.mainscreen.global.MainPresenter;
import com.example.jamal.pastebin.mvp.mainscreen.global.MainView;

import static com.example.jamal.pastebin.utils.RouterUtils.showFragment;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenter();
        mainPresenter.attachView(this);
        initViews();
    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void startFragment(Fragment fragment) {
        showFragment(R.id.FrameLayout_main_container,fragment,this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
    }

    private void initViews() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.BottomNavigationView_main);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            mainPresenter.onBottomNavigationSelectClick(item.getItemId());
            return true;
        });
    }
}