package com.example.jamal.pastebin.ui.mainscreen.account.listpaste;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.jamal.pastebin.App;
import com.example.jamal.pastebin.R;
import com.example.jamal.pastebin.data.models.PasteNetwork;
import com.example.jamal.pastebin.mvp.mainscreen.account.listpaste.MyPastePresenter;
import com.example.jamal.pastebin.mvp.mainscreen.account.listpaste.MyPasteView;
import com.example.jamal.pastebin.ui.mainscreen.listpaste.PasteAdapter;

import java.util.List;

public class MyPasteFragment extends Fragment implements MyPasteView {

    private MyPastePresenter presenter;

    private ProgressBar progressBar;

    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MyPastePresenter(App.getDataManager());
        presenter.attachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_paste_by_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        presenter.showListPaste();
    }

    @Override
    public void showProgress(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showListPaste(List<PasteNetwork> pasteNetworkList) {
         recyclerView.setAdapter(new PasteAdapter(pasteNetworkList));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }

    private void initView(View view) {
        recyclerView= view.findViewById(R.id.RecyclerView_list_paste_user);
        progressBar = view.findViewById(R.id.ProgressBar_listPasteByUser);
    }
}