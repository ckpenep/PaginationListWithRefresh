package com.example.ckpenep.testtaskagileengine.ui.fragments.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.ckpenep.testtaskagileengine.R;

/**
 * Created by ckpenep on 26.03.2018.
 */

public class HomeFragment extends MvpAppCompatFragment implements HomeView {

    private TextView mUserName;

    @InjectPresenter
    HomePresenter presenter;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUserName = (TextView) view.findViewById(R.id.user_name);
    }

    @Override
    public void setUserName(String userName) {
        mUserName.setText(userName);
    }
}
