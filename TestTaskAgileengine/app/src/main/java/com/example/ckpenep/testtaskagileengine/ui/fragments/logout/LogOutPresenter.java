package com.example.ckpenep.testtaskagileengine.ui.fragments.logout;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.ckpenep.testtaskagileengine.constants.Constants;
import com.example.ckpenep.testtaskagileengine.utils.PrefUtils;

/**
 * Created by ckpenep on 26.03.2018.
 */
@InjectViewState
public class LogOutPresenter extends MvpPresenter<LogOutView> {
    public void signOut() {
        PrefUtils.setPreference(Constants.TOKEN, "");

        getViewState().signOut();
    }
}
