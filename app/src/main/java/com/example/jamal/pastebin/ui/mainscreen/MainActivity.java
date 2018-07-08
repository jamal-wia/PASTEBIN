package com.example.jamal.pastebin.ui.mainscreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.jamal.pastebin.R;
import com.example.jamal.pastebin.ui.mainscreen.listpaste.ListPasteFragment;
import com.example.jamal.pastebin.utils.CommonUtils;
import com.example.jamal.pastebin.utils.RouterUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RouterUtils.showFragment(
                R.id.FrameLayout_main_container,
                new ListPasteFragment(),
                this);
    }
}