package com.example.ckpenep.testtaskagileengine.ui.activities.main;

import com.arellomobile.mvp.MvpView;

/**
 * Created by ckpenep on 23.03.2018.
 */

public interface MainView extends MvpView {
    String HOME_TAB_POSITION = "Home";
    String LIST_TAB_POSITION = "Stackoverflow";
    String LOGOUT_TAB_POSITION = "Log Out";

    void setToolbarTitle(String title);
}
