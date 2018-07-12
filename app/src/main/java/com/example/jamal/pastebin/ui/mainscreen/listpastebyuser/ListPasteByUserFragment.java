package com.example.jamal.pastebin.ui.mainscreen.listpastebyuser;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.jamal.pastebin.App;
import com.example.jamal.pastebin.R;
import com.example.jamal.pastebin.data.models.PasteByUser;
import com.example.jamal.pastebin.mvp.mainscreen.listpaste.ListPasteByUserPresenter;
import com.example.jamal.pastebin.mvp.mainscreen.listpaste.ListPasteByUserView;

import java.util.List;

public class ListPasteByUserFragment extends Fragment implements ListPasteByUserView {

    private ListPasteByUserPresenter listPasteByUserPresenter;

    private ProgressBar progressBar;

    private RecyclerView recyclerView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listPasteByUserPresenter = new ListPasteByUserPresenter(App.getDataManager());
        listPasteByUserPresenter.attachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_paste_by_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        listPasteByUserPresenter.showListPaste();
    }

    @Override
    public void showProgress(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showListPaste(List<PasteByUser> pasteByUserList) {
         recyclerView.setAdapter(new ListPasteByUserAdapter(pasteByUserList));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        listPasteByUserPresenter.detachView();
    }

    private void initView(View view) {
        recyclerView= view.findViewById(R.id.RecyclerView_list_paste_user);
        progressBar = view.findViewById(R.id.ProgressBar_listPasteByUser);
    }
}