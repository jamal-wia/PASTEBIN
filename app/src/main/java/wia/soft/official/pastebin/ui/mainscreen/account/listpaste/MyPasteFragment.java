package wia.soft.official.pastebin.ui.mainscreen.account.listpaste;

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

import com.example.jamal.pastebin.R;

import java.util.List;

import wia.soft.official.pastebin.App;
import wia.soft.official.pastebin.data.models.PasteNetwork;
import wia.soft.official.pastebin.data.models.PasteRoom;
import wia.soft.official.pastebin.mvp.mainscreen.account.listpaste.MyPastePresenter;
import wia.soft.official.pastebin.mvp.mainscreen.account.listpaste.MyPasteView;
import wia.soft.official.pastebin.ui.auth.AuthActivity;
import wia.soft.official.pastebin.ui.infopaste.InfoPasteActivity;
import wia.soft.official.pastebin.utils.CommonUtils;

import static wia.soft.official.pastebin.utils.CommonUtils.showStandartDialogWindowsWithSecondButton;

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
                startActivity(InfoPasteActivity.getStartIntent(getActivity(), paste)));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showDialogWindow(PasteRoom paste) {
        String[] items = {"Save", "Share", "View in...", "Remove this paste"};
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
                            Intent viewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(paste.getUrl()));
                            startActivity(viewIntent);
                            break;
                        case 3:
                            presenter.removePaste(paste);
                            break;
                    }
                }).create();
        alertDialog.show();
        alertDialog.show();
    }

    @Override
    public void removePaste(PasteRoom pasteRoom) {
        presenter.showListPaste();
    }

    @Override
    public void showMessage(String message) {
        CommonUtils.showToastShort(getContext(), message);
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

        exitFloatingActionButton = view.findViewById(R.id.FloatingActionButton_lispPaste_exit);
        exitFloatingActionButton.setOnClickListener(v -> {

            showStandartDialogWindowsWithSecondButton(
                    getContext(),
                    getResources().getString(R.string.warning),
                    getResources().getString(R.string.go_out),
                    getResources().getString(R.string.exit),
                    getResources().getString(R.string.cancel),
                    () -> {

                        presenter.exit();
                        startActivity(new Intent(getActivity(), AuthActivity.class));
                        getActivity().finish();
                    },
                    () -> {
                    }
            );
        });
    }
}