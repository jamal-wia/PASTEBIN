package com.example.jamal.pastebin.ui.mainscreen.listpaste.saved;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
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
import com.example.jamal.pastebin.ui.infopaste.InfoPasteActivity;

import java.util.List;

import static android.content.Intent.EXTRA_TEXT;

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
        SavedPasteAdapter adapter = new SavedPasteAdapter(pasteRooms);
        adapter.setItemLongClickListener(pasteRoom -> {
            presenter.itemLongClick(pasteRoom);
        });
        adapter.setItemClickListener(pasteRoom -> {
            String s = pasteRoom.getCode();
            startActivity(InfoPasteActivity.getStartIntent(getActivity(), pasteRoom));
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showDialogWindow(PasteRoom pasteRoom) {
        String[] items = {"Share", "View in...", "delete"};
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("Selected action")
                .setItems(items, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            Intent shareIntent = new Intent(Intent.ACTION_SEND);
                            shareIntent.putExtra(EXTRA_TEXT, pasteRoom.getUrl());
                            shareIntent.setType("text/plain");
                            startActivity(shareIntent);
                            break;
                        case 1:
                            Intent viewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pasteRoom.getUrl()));
                            startActivity(viewIntent);
                            break;
                        case 2:
                            presenter.delete(pasteRoom);
                            break;
                    }
                }).create();
        alertDialog.show();
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