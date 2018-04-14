package com.example.ckpenep.testtaskagileengine.ui.fragments.logout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.ckpenep.testtaskagileengine.R;
import com.example.ckpenep.testtaskagileengine.ui.activities.splash.SplashActivity;

/**
 * Created by ckpenep on 26.03.2018.
 */

public class LogOutFragment extends MvpAppCompatFragment implements LogOutView {

    @InjectPresenter
    LogOutPresenter mLogOutPresenter;

    private Button logOutButton;

    public static LogOutFragment newInstance() {
        LogOutFragment fragment = new LogOutFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_logout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logOutButton = (Button) view.findViewById(R.id.logoutButton);

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLogOutPresenter.signOut();
            }
        });
    }

    @Override
    public void signOut() {
        final Intent intent = new Intent(getActivity(), SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }
}
