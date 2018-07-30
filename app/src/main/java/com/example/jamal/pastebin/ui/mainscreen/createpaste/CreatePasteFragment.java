package com.example.jamal.pastebin.ui.mainscreen.createpaste;

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
import com.example.jamal.pastebin.mvp.mainscreen.createpaste.CreatePastePresenter;
import com.example.jamal.pastebin.mvp.mainscreen.createpaste.CreatePasteView;
import com.example.jamal.pastebin.utils.CommonUtils;

import static com.example.jamal.pastebin.utils.CommonUtils.showToastShort;

public class CreatePasteFragment extends Fragment implements CreatePasteView {

    private CreatePastePresenter presenter;

    private EditText titleEditText;
    private EditText privateEditText;
    private EditText expireDataEditText;
    private EditText formatEditText;
    private EditText codeEditText;

    private Button createButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_creat_paste, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new CreatePastePresenter(App.getDataManager());
        presenter.attachView(this);
        initViews(view);
    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void showMessage(String message) {
        showToastShort(getActivity(), message);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }

    private void initViews(@NonNull View view) {
        titleEditText = view.findViewById(R.id.EditText_createPaste_title);
        privateEditText = view.findViewById(R.id.EditText_createPaste_private);
        expireDataEditText = view.findViewById(R.id.EditText_createPaste_expireData);
        formatEditText = view.findViewById(R.id.EditText_createPaste_format);
        codeEditText = view.findViewById(R.id.EditText_createPaste_code);

        createButton = view.findViewById(R.id.Button_createPaste_create);
        createButton.setOnClickListener(v ->
                presenter.createButtonClick(
                        titleEditText.getText().toString(),
                        formatEditText.getText().toString(),
                        Integer.valueOf(privateEditText.getText().toString()),
                        expireDataEditText.getText().toString(),
                        codeEditText.getText().toString()));
    }
}