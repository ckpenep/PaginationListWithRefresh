package com.example.ckpenep.testtaskagileengine.ui.activities.splash;

import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.ckpenep.testtaskagileengine.ui.activities.login.LoginActivity;
import com.example.ckpenep.testtaskagileengine.ui.activities.main.MainActivity;

/**
 * Created by ckpenep on 26.03.2018.
 */

public class SplashActivity extends MvpAppCompatActivity implements SplashView {

    @InjectPresenter
    SplashPresenter mSplashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getMvpDelegate().onAttach();
    }

    @Override
    public void setAuthorized(boolean isAuthorized) {
        startActivity(new Intent(this, isAuthorized ? MainActivity.class : LoginActivity.class));
    }
}
