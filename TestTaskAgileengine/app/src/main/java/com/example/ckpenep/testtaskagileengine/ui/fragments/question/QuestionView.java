package com.example.ckpenep.testtaskagileengine.ui.fragments.question;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.ckpenep.testtaskagileengine.model.Question;

import java.util.List;

/**
 * Created by ckpenep on 26.03.2018.
 */

public interface QuestionView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void showError(String error);

    void hideProgressBar();

    void hideError();

    void showRefreshing();

    void hideRefreshing();

    void showListProgress();

    void hideListProgress();

    void activateLastItemViewListener();

    void disableLastItemViewListener();

    void refreshRepositories(List<Question> repositories, boolean maybeMore);

    @StateStrategyType(AddToEndStrategy.class)
    void addRepositories(List<Question> repositories, boolean maybeMore);
}
