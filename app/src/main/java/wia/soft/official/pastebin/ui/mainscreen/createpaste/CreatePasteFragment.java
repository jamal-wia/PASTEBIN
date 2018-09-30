package wia.soft.official.pastebin.ui.mainscreen.createpaste;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.jamal.pastebin.R;

import wia.soft.official.pastebin.App;
import wia.soft.official.pastebin.mvp.mainscreen.createpaste.CreatePastePresenter;
import wia.soft.official.pastebin.mvp.mainscreen.createpaste.CreatePasteView;

import static wia.soft.official.pastebin.utils.CommonUtils.showToastShort;

public class CreatePasteFragment extends Fragment implements CreatePasteView {

    private CreatePastePresenter presenter;

    private EditText titleEditText;
    private EditText codeEditText;

    private String expireData;
    private int privateInt;
    private String languageFormat;

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
        presenter.cancelRequest();
        presenter.detachView();
    }

    private void initViews(@NonNull View view) {
        titleEditText = view.findViewById(R.id.EditText_createPaste_title);
        codeEditText = view.findViewById(R.id.EditText_createPaste_code);
        initSpinner(view);

        RadioGroup privateRadioGroup = view.findViewById(R.id.RadioGroup_createPaste_private);
        privateRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case 0:
                    privateInt = 0;
                    break;
                case 1:
                    privateInt = 1;
                    break;
            }
        });
        RadioGroup expireDataRadioGroup = view.findViewById(R.id.RadioGroup_createPaste_expireData);
        expireDataRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case 0:
                    expireData = "N";
                    break;
                case 1:
                    expireData = "10M";
                    break;
                case 2:
                    expireData = "1H";
                    break;
            }
        });

        FloatingActionButton createButton = view.findViewById(R.id.FloatingButton_createPaste_create);
        createButton.setOnClickListener(v ->
                presenter.createButtonClick(
                        titleEditText.getText().toString(),
                        languageFormat,
                        privateInt,
                        expireData,
                        codeEditText.getText().toString()
                )
        );
    }

    private void initSpinner(View view) {
        Spinner spinnerFormat = view.findViewById(R.id.Spinner_createPaste_format);
        ArrayAdapter<?> arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.language,
                android.R.layout.simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFormat.setAdapter(arrayAdapter);
        String[] languagePref = getResources().getStringArray(R.array.language_pref);
        spinnerFormat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                languageFormat = languagePref[i];
                codeEditText.setHint(getResources().getStringArray(R.array.language)[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}