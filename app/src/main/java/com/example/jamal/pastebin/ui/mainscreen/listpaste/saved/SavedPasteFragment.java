package com.example.jamal.pastebin.ui.mainscreen.listpaste.saved;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jamal.pastebin.App;
import com.example.jamal.pastebin.R;
import com.example.jamal.pastebin.data.models.PasteRoom;
import com.example.jamal.pastebin.mvp.mainscreen.listpaste.saved.SavedPastePresenter;
import com.example.jamal.pastebin.mvp.mainscreen.listpaste.saved.SavedPasteView;

import java.util.List;

public class SavedPasteFragment extends Fragment implements SavedPasteView {
    private SavedPastePresenter presenter;

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_saved_paste, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new SavedPastePresenter(App.getDataManager());
        presenter.attachView(this);
        initViews(view);
        presenter.showListSaved();
    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void showListSaved(List<PasteRoom> pasteRooms) {
        recyclerView.setAdapter(new SavedPasteAdapter(pasteRooms));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.RecyclerView_savedPaste);
    }
}