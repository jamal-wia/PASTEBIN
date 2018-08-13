package com.example.jamal.pastebin.ui.mainscreen.account;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jamal.pastebin.App;
import com.example.jamal.pastebin.R;
import com.example.jamal.pastebin.data.models.User;
import com.example.jamal.pastebin.mvp.mainscreen.account.AccountPresenter;
import com.example.jamal.pastebin.mvp.mainscreen.account.AccountView;
import com.example.jamal.pastebin.ui.mainscreen.account.listpaste.MyPasteFragment;
import com.example.jamal.pastebin.utils.CommonUtils;
import com.example.jamal.pastebin.utils.RouterUtils;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountFragment extends Fragment implements AccountView {
    private AccountPresenter presenter;

    private CircleImageView avatarCircleImageView;

    private TextView nameTextView;
    private TextView emailTextView;
    private TextView websiteTextView;
    private TextView locationTextView;
    private TextView typeTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new AccountPresenter(App.getDataManager());
        presenter.attachView(this);
        initViews(view);
        presenter.showInfoUser();
    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void editColorType(int color) {
        typeTextView.setTextColor(getResources().getColor(color));
    }

    @Override
    public void showMessage(String noNetwork) {
        CommonUtils.showToastShort(getActivity(),noNetwork);
    }

    @Override
    public void showInfoUser(User user) {
        Picasso.get().load(user.getAvatarUrl()).into(avatarCircleImageView);

        nameTextView.setText(user.getName());
        emailTextView.setText(user.getEmail());
        websiteTextView.setText(user.getWebsite());
        locationTextView.setText(user.getLocation());
        typeTextView.setText(user.getAccountType());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.cancelRequest();
        presenter.detachView();
    }

    @SuppressLint("ResourceAsColor")
    private void initViews(View view) {
        avatarCircleImageView = view.findViewById(R.id.CircleImageView_account_avatar);

        nameTextView = view.findViewById(R.id.TextView_account_name);
        emailTextView = view.findViewById(R.id.TextView_account_email);
        websiteTextView = view.findViewById(R.id.TextView_account_website);
        locationTextView = view.findViewById(R.id.TextView_account_location);
        typeTextView = view.findViewById(R.id.TextView_account_type);

        RouterUtils.showFragment(R.id.FrameLayout_account_container,new MyPasteFragment(),getActivity());
    }
}