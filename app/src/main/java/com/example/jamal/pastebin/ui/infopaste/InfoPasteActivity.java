package com.example.jamal.pastebin.ui.infopaste;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.jamal.pastebin.App;
import com.example.jamal.pastebin.R;
import com.example.jamal.pastebin.data.models.PasteRoom;
import com.example.jamal.pastebin.mvp.infopaste.InfoPastePresenter;
import com.example.jamal.pastebin.mvp.infopaste.InfoPasteView;

import static android.content.Intent.EXTRA_TEXT;

public class InfoPasteActivity extends AppCompatActivity implements InfoPasteView {
    private InfoPastePresenter presenter;

    private static final String EXTRA_PASTE = "EXTRA_PASTE";

    private TextView mainTextView;

    private PasteRoom pasteRoom;

    public static Intent getStartIntent(Context context, PasteRoom paste) {
        Intent intent = new Intent(context, InfoPasteActivity.class);
        intent.putExtra(EXTRA_PASTE, paste);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_paste);
        presenter = new InfoPastePresenter(App.getDataManager());
        presenter.attachView(this);
        pasteRoom = getIntent().getParcelableExtra(EXTRA_PASTE);
        initViews();

        if (pasteRoom.getCode() == null) presenter.showRawPaste(pasteRoom.getKey());
        else showPaste(pasteRoom.getCode());
    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void showPaste(String code) {
        mainTextView.setText(code);
    }

    @Override
    public void showTrendingPaste(String apiPasteKey) {
        presenter.showRawTrendingPaste(apiPasteKey);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.cancelRequest();
        presenter.detachView();
    }

    @SuppressLint("RestrictedApi")
    private void initViews() {
        Toolbar toolbar = findViewById(R.id.Toolbar_infoPaste);
        toolbar.setTitle(pasteRoom.getTitle());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mainTextView = findViewById(R.id.TextView_infoPaste_main);
    }
}