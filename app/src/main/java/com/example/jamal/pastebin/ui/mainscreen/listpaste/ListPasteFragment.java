package com.example.jamal.pastebin.ui.mainscreen.listpaste;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jamal.pastebin.R;
import com.example.jamal.pastebin.mvp.mainscreen.listpaste.ListPastePresenter;
import com.example.jamal.pastebin.mvp.mainscreen.listpaste.ListPasteView;
import com.example.jamal.pastebin.ui.mainscreen.account.listpaste.MyPasteFragment;
import com.example.jamal.pastebin.ui.mainscreen.listpaste.ViewPagerAdapter;
import com.example.jamal.pastebin.ui.mainscreen.listpaste.saved.SavedPasteFragment;
import com.example.jamal.pastebin.ui.mainscreen.listpaste.trending.TrendingFragment;
import com.example.jamal.pastebin.utils.RouterUtils;

public class ListPasteFragment extends Fragment implements ListPasteView {

    private ListPastePresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_paste, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new ListPastePresenter();
        presenter.attachView(this);
        presenter.startView();
        initViews(view);
    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void showFragment(Fragment fragment) {
        RouterUtils.showFragment(R.id.ViewPager_listPaste,fragment,getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }

    private void initViews(@NonNull View view){
        Toolbar toolbar = view.findViewById(R.id.Toolbar_listPaste);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(null);

        ViewPager viewPager = view.findViewById(R.id.ViewPager_listPaste);
        setupViewPager(viewPager);

        TabLayout tabLayout = view.findViewById(R.id.TabLayout_listPaste);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new SavedPasteFragment(), "Save Paste");
        adapter.addFragment(new TrendingFragment(), "Trending");
        viewPager.setAdapter(adapter);
    }
}