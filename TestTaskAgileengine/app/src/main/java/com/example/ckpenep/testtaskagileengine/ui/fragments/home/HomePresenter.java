package com.example.ckpenep.testtaskagileengine.ui.fragments.home;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.ckpenep.testtaskagileengine.constants.Constants;
import com.example.ckpenep.testtaskagileengine.utils.PrefUtils;

/**
 * Created by ckpenep on 26.03.2018.
 */

@InjectViewState
public class HomePresenter extends MvpPresenter<HomeView> {
    public HomePresenter() {}

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getUserName();
    }

    private void getUserName() {
        String result = PrefUtils.getPreference(Constants.USER_NAME, "");

        getViewState().setUserName(result);
    }
}
