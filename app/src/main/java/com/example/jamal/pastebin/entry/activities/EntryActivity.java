package com.example.jamal.pastebin.entry.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.jamal.pastebin.R;
import com.example.jamal.pastebin.entry.fragments.InputMailFragment;
import com.example.jamal.pastebin.entry.fragments.LoginFragment;
import com.example.jamal.pastebin.entry.fragments.NameAndPasswordFragment;
import com.example.jamal.pastebin.utils.DialogUtils;

import static com.example.jamal.pastebin.utils.FragmentUtils.showFragment;
import static com.example.jamal.pastebin.utils.FragmentUtils.showFragmentInStack;

public class EntryActivity extends AppCompatActivity {

    Button switchFragmentsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        initViews();

        showFragment(R.id.frameLayout_entry_container, new LoginFragment(), this);
        switchFragmentsButton.setOnClickListener(v -> selectFragments());
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentById(R.id.frameLayout_entry_container)
                instanceof NameAndPasswordFragment) {
            new DialogUtils(super::onBackPressed, () -> { }).showStandartDialogWindow(this, "You're almost there!",
                    "Do you really want to come back?", "Back", "Cancel");
        }else {
            super.onBackPressed();
        }
    }

    private void selectFragments() {
        if (getSupportFragmentManager().findFragmentById(R.id.frameLayout_entry_container)
                instanceof LoginFragment) {
            switchFragmentsButton.setText(R.string.entry_activity_already_have_account);
            showFragmentInStack(R.id.frameLayout_entry_container, new InputMailFragment(), this);
        } else {
            switchFragmentsButton.setText(R.string.entry_activity_do_not_have_account);
            showFragmentInStack(R.id.frameLayout_entry_container, new LoginFragment(), this);
        }
    }

    private void initViews() {
        switchFragmentsButton = findViewById(R.id.button_entry_controlFragments);
    }
}