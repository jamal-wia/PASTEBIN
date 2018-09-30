package wia.soft.official.pastebin.ui.auth;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.jamal.pastebin.R;

import wia.soft.official.pastebin.ui.auth.login.LoginFragment;

import static wia.soft.official.pastebin.utils.RouterUtils.showFragment;

public class AuthActivity extends AppCompatActivity {

    Button switchFragmentsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        initViews();
        showFragment(R.id.FrameLayout_auth_container, new LoginFragment(), this);
    }

    private void initViews() {
        switchFragmentsButton = findViewById(R.id.Button_Auth_controlFragments);
        switchFragmentsButton.setOnClickListener(v -> {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://pastebin.com/signup")));
        });
    }
}