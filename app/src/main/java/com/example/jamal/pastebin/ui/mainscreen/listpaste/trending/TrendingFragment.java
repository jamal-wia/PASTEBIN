package com.example.jamal.pastebin.ui.mainscreen.listpaste.trending;

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
import com.example.jamal.pastebin.mvp.mainscreen.listpaste.trending.TrendingPresenter;
import com.example.jamal.pastebin.mvp.mainscreen.listpaste.trending.TrendingView;
import com.example.jamal.pastebin.ui.mainscreen.listpaste.PasteAdapter;

import java.util.List;

public class TrendingFragment extends Fragment implements TrendingView {

    private TrendingPresenter presenter;

    private RecyclerView listTrendingPasteRecyclerView;

    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trending_paste, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new TrendingPresenter(App.getDataManager());
        presenter.attachView(this);
        initViews(view);
        presenter.showListTrendingPaste();
    }

    @Override
    public void showProgress(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showListTrendingPaste(List<PasteNetwork> pasteNetworks) {
        listTrendingPasteRecyclerView.setAdapter(new PasteAdapter(pasteNetworks));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }

    private void initViews(@NonNull View view) {
        listTrendingPasteRecyclerView = view.findViewById(R.id.RecyclerView_listTrendingPaste_listPaste);
        progressBar = view.findViewById(R.id.ProgressBar_trendingPaste);
    }
}