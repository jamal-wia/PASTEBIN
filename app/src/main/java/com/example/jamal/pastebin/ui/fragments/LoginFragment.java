package com.example.jamal.pastebin.ui.fragments;

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

import com.example.jamal.pastebin.App;
import com.example.jamal.pastebin.R;
import com.example.jamal.pastebin.ui.activities.MainActivity;
import com.example.jamal.pastebin.utils.Constants;
import com.example.jamal.pastebin.utils.StandardDialogWindow;

import java.io.IOException;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.jamal.pastebin.utils.CommonUtils.showToastLong;

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
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        assert response.body() != null;
                        App.getPreferencesHelper().saveToken(Objects.requireNonNull(response.body()).string());
                        switch (App.getPreferencesHelper().getToken()) {
                            case "Bad API request, use POST request, not GET":
                                showToastLong(getActivity(), "Bad API request, use POST request, not GET");
                                break;
                            case "Bad API request, invalid api_dev_key":
                                showToastLong(getActivity(), "Bad API request, invalid api_dev_key");
                                break;
                            case "Bad API request, invalid login":
                                showToastLong(getActivity(), "Bad API request, invalid login");
                                break;
                            case "Bad API request, account not active":
                                showToastLong(getActivity(), "Bad API request, account not active");
                                break;
                            case "Bad API request, invalid POST parameters":
                                showToastLong(getActivity(), "Bad API request, invalid POST parameters");
                                break;
                            default:
                                showMainScreen();
                                break;
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                new StandardDialogWindow((StandardDialogWindow.OnClickPositiveButtonListener) () -> {

                }).withPositiveButton(getActivity(), getString(R.string.error), getString(R.string.network_error), getString(R.string.back));
            }
        });
    }

    private void showMainScreen() {
        startActivity(new Intent(getActivity(), MainActivity.class));
        Objects.requireNonNull(getActivity()).finish();
    }

    private void initViews(@NonNull View view) {
        userNameEdit = view.findViewById(R.id.edit_login_username);
        passwordEdit = view.findViewById(R.id.edit_login_password);
        loginButton = view.findViewById(R.id.button_login);
        loginButton.setOnClickListener(v -> callRequest());
    }
}