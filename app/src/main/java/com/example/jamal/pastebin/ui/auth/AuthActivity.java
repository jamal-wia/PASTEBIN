package com.example.jamal.pastebin.ui.auth;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.jamal.pastebin.R;
import com.example.jamal.pastebin.ui.auth.login.LoginFragment;

import static com.example.jamal.pastebin.utils.FragmentUtils.showFragment;

public class AuthActivity extends AppCompatActivity {

    Button switchFragmentsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        initViews();
        showFragment(R.id.frameLayout_entry_container, new LoginFragment(), this);
    }

    private void initViews() {
        switchFragmentsButton = findViewById(R.id.button_entry_controlFragments);
        switchFragmentsButton.setOnClickListener(v -> {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://pastebin.com/signup")));
        });
    }
}