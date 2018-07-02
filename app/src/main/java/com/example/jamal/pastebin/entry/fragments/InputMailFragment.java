package com.example.jamal.pastebin.entry.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jamal.pastebin.R;

import static com.example.jamal.pastebin.utils.FragmentUtils.showFragmentInStack;

public class InputMailFragment extends Fragment {

    Button nextButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input_mail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initViews(view);

        nextButton.setOnClickListener(v -> showFragmentInStack(R.id.frameLayout_entry_container,
                new NameAndPasswordFragment(), getActivity()));
    }

    private void initViews(@NonNull View view) {
        nextButton = view.findViewById(R.id.button_inputMail_next);
    }
}