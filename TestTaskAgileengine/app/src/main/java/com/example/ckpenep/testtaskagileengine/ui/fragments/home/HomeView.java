package com.example.ckpenep.testtaskagileengine.ui.fragments.home;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by ckpenep on 26.03.2018.
 */

@StateStrategyType(AddToEndSingleStrategy.class)
public interface HomeView extends MvpView {
    void setUserName(String userName);
}
