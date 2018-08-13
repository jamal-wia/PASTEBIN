package com.example.jamal.pastebin.ui.mainscreen.account.listpaste;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.jamal.pastebin.App;
import com.example.jamal.pastebin.R;
import com.example.jamal.pastebin.data.models.PasteNetwork;
import com.example.jamal.pastebin.data.models.PasteRoom;
import com.example.jamal.pastebin.mvp.mainscreen.account.listpaste.MyPastePresenter;
import com.example.jamal.pastebin.mvp.mainscreen.account.listpaste.MyPasteView;
import com.example.jamal.pastebin.ui.auth.AuthActivity;
import com.example.jamal.pastebin.ui.infopaste.InfoPasteActivity;
import com.example.jamal.pastebin.utils.RouterUtils;

import java.util.List;

import static com.example.jamal.pastebin.utils.RouterUtils.showActivity;

public class MyPasteFragment extends Fragment implements MyPasteView {
    private MyPastePresenter presenter;

    private ProgressBar progressBar;

    private RecyclerView recyclerView;

    private FloatingActionButton exitFloatingActionButton;

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
        MyPasteAdapter adapter = new MyPasteAdapter(pasteNetworkList);
        adapter.setItemLongClickListener(paste -> presenter.showDialogWindow(paste));
        adapter.setItemClickListener(paste ->
                startActivity(InfoPasteActivity.getStartIntent(getActivity(),paste)));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showDialogWindow(PasteRoom paste) {
        String[] items = {"Save", "Share", "View in..."};
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("Selected action")
                .setItems(items, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            presenter.insertPaste(paste);
                            break;
                        case 1:
                            Intent shareIntent = new Intent(Intent.ACTION_SEND);
                            shareIntent.putExtra(Intent.EXTRA_TEXT, paste.getUrl());
                            shareIntent.setType("text/plain");
                            startActivity(shareIntent);
                            break;
                        case 2:
                            Intent viewIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(paste.getUrl()));
                            startActivity(viewIntent);
                            break;
                    }
                }).create();
        alertDialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.cancelRequest();
        presenter.detachView();
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.RecyclerView_list_paste_user);
        progressBar = view.findViewById(R.id.ProgressBar_listPasteByUser);

        exitFloatingActionButton= view.findViewById(R.id.FloatingActionButton_lispPaste_exit);
        exitFloatingActionButton.setOnClickListener(v->{
            presenter.exit();
            startActivity(new Intent(getActivity(), AuthActivity.class));
            getActivity().finish();
        });
    }
}