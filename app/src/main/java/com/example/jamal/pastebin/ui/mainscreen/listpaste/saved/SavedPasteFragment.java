package com.example.jamal.pastebin.ui.mainscreen.listpaste.saved;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jamal.pastebin.App;
import com.example.jamal.pastebin.R;
import com.example.jamal.pastebin.mvp.mainscreen.listpaste.saved.SavedPastePresenter;
import com.example.jamal.pastebin.mvp.mainscreen.listpaste.saved.SavedPasteView;

public class SavedPasteFragment extends Fragment implements SavedPasteView{

    private SavedPastePresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_saved_paste, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter= new SavedPastePresenter(App.getDataManager());
        presenter.attachView(this);
    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }
}