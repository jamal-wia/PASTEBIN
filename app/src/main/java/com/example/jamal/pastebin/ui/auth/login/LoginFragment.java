package com.example.jamal.pastebin.ui.auth.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.jamal.pastebin.App;
import com.example.jamal.pastebin.R;
import com.example.jamal.pastebin.mvp.auth.login.LoginPresenter;
import com.example.jamal.pastebin.mvp.auth.login.LoginView;
import com.example.jamal.pastebin.MainActivity;

import static com.example.jamal.pastebin.utils.CommonUtils.showToastShort;

public class LoginFragment extends Fragment implements LoginView {

    private LoginPresenter loginPresenter;

    private EditText userNameEdit;
    private EditText passwordEdit;
    private Button loginButton;
    private ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginPresenter = new LoginPresenter(App.getDataManager());
        loginPresenter.attachView(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    @Override
    public void showProgress(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMessage(String message) {
        showToastShort(getActivity(), message);
    }

    @Override
    public void showEmptyLoginError() {
        userNameEdit.setError(getString(R.string.empty_field));
        userNameEdit.requestFocus();
    }

    @Override
    public void showEmptyPasswordError() {
        passwordEdit.setError(getString(R.string.empty_field));
        passwordEdit.requestFocus();
    }

    @Override
    public void navigateToMain() {
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        loginPresenter.detachView();
    }

    private void initViews(@NonNull View view) {
        userNameEdit = view.findViewById(R.id.edit_login_username);
        passwordEdit = view.findViewById(R.id.edit_login_password);

        progressBar = view.findViewById(R.id.progress_login);

        loginButton = view.findViewById(R.id.button_login);
        loginButton.setOnClickListener(v -> loginPresenter.login(
                userNameEdit.getText().toString(),
                passwordEdit.getText().toString()));
    }
}