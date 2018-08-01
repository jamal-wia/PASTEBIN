package com.example.jamal.pastebin.ui.mainscreen.account;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jamal.pastebin.App;
import com.example.jamal.pastebin.R;
import com.example.jamal.pastebin.mvp.mainscreen.account.AccountPresenter;
import com.example.jamal.pastebin.mvp.mainscreen.account.AccountView;

public class AccountFragment extends Fragment implements AccountView {

    private AccountPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new AccountPresenter(App.getDataManager());
        presenter.attachView(this);
        presenter.showInfoUser();
    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void showInfoUser() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }
}