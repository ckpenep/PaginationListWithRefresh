package com.example.ckpenep.testtaskagileengine.ui.activities.login;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by ckpenep on 23.03.2018.
 */

public interface LoginView extends MvpView {

    void startLogIn();

    void finishLogIn();

    void hideFormError();

    void showFormError(Integer nameError, Integer passwordError);

    @StateStrategyType(SkipStrategy.class)
    void successLogIn();
}
