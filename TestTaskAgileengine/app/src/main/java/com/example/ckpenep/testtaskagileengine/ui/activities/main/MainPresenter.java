package com.example.ckpenep.testtaskagileengine.ui.activities.main;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.ckpenep.testtaskagileengine.Screens;

import ru.terrakok.cicerone.Router;

/**
 * Created by ckpenep on 23.03.2018.
 */
@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    Router router;

    public MainPresenter(Router router) {
        this.router = router;
    }

    public void onTabHomeClick() {
        getViewState().setToolbarTitle(MainView.HOME_TAB_POSITION);
        router.replaceScreen(Screens.HOME_SCREEN);
    }

    public void onTabListClick() {
        getViewState().setToolbarTitle(MainView.LIST_TAB_POSITION);
        router.replaceScreen(Screens.QUESTIONS_SCREEN);
    }

    public void onTabLogoutClick() {
        getViewState().setToolbarTitle(MainView.LOGOUT_TAB_POSITION);
        router.replaceScreen(Screens.LOGOUT_SCREEN);
    }

    public void onBackPressed() {
        router.exit();
    }

}
