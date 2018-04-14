package com.example.ckpenep.testtaskagileengine.ui.activities.splash;

import android.text.TextUtils;

import com.arellomobile.mvp.MvpPresenter;
import com.example.ckpenep.testtaskagileengine.constants.Constants;
import com.example.ckpenep.testtaskagileengine.utils.PrefUtils;

/**
 * Created by ckpenep on 26.03.2018.
 */

public class SplashPresenter extends MvpPresenter<SplashView> {

    @Override
    public void attachView(SplashView view) {
        super.attachView(view);

        view.setAuthorized(!TextUtils.isEmpty(PrefUtils.getPreference(Constants.TOKEN, "")));
    }
}
