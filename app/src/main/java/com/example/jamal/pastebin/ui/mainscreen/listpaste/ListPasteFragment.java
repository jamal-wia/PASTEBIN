package com.example.jamal.pastebin.ui.mainscreen.listpaste;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jamal.pastebin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListPasteFragment extends Fragment {


    public ListPasteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_paste, container, false);
    }

}
