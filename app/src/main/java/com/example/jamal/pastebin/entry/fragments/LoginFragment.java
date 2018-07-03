package com.example.jamal.pastebin.entry.fragments;

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

import com.example.jamal.pastebin.MainActivity;
import com.example.jamal.pastebin.R;
import com.example.jamal.pastebin.App;
import com.example.jamal.pastebin.utils.Constants;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    private EditText userNameEdit;
    private EditText passwordEdit;
    private Button loginButton;

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

    private void callRequest() {
        Call<ResponseBody> responseBodyCall = App.getPastebinServise().login(
                Constants.DEV_KEY,
                userNameEdit.getText().toString(),
                passwordEdit.getText().toString());
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        App.getPreferencesHelper().saveToken( response.body().string());
                        showMainScreen();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private  void showMainScreen(){
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }

    private void initViews(@NonNull View view) {
        userNameEdit = view.findViewById(R.id.edit_login_username);
        passwordEdit = view.findViewById(R.id.edit_login_password);
        loginButton = view.findViewById(R.id.button_login);
        loginButton.setOnClickListener(v-> callRequest());
    }
}