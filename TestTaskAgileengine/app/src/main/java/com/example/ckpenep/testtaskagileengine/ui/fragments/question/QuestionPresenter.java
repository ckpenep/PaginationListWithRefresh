package com.example.ckpenep.testtaskagileengine.ui.fragments.question;

import android.os.Handler;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.ckpenep.testtaskagileengine.App;
import com.example.ckpenep.testtaskagileengine.common.Utils;
import com.example.ckpenep.testtaskagileengine.mappers.QuestionMapper;
import com.example.ckpenep.testtaskagileengine.model.Question;
import com.example.ckpenep.testtaskagileengine.model.dto.question.QuestionItem;
import com.example.ckpenep.testtaskagileengine.model.dto.question.QuestionsList;
import com.example.ckpenep.testtaskagileengine.providers.StackoverflowService;
import com.example.ckpenep.testtaskagileengine.server.API;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * Created by ckpenep on 26.03.2018.
 */
@InjectViewState
public class QuestionPresenter extends MvpPresenter<QuestionView> {

    @Inject
    StackoverflowService mStackoverflowService;

    private Disposable subscription = Disposables.empty();

    private boolean mIsInLoading;

    public QuestionPresenter() {
        App.getAppComponent().inject(this);
    }

    @Override
    public void onDestroy() {
        if (!subscription.isDisposed()) {
            subscription.dispose();
        }
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadRepositories(false);
    }

    public void loadNextRepositories(int currentCount) {
        int page = currentCount / API.PAGE_SIZE + 1;

        loadData(page, true, false);
    }

    public void loadRepositories(boolean isRefreshing) {
        loadData(1, false, isRefreshing);
    }

    private void loadData(int page, boolean isPageLoading, boolean isRefreshing) {
        if (mIsInLoading) {
            return;
        }

        mIsInLoading = true;

        getViewState().disableLastItemViewListener();
        showProgress(isPageLoading, isRefreshing);

        final Observable<QuestionsList> observable = mStackoverflowService.getUserRepos(page, API.PAGE_SIZE, "react-native");

        if (!subscription.isDisposed()) {
            subscription.dispose();
        }

        new Handler().postDelayed(() ->
                subscription = observable
                        .compose(Utils.applySchedulers())
                        .subscribe(questions -> {
                            onLoadingFinish(isPageLoading, isRefreshing);
                            onLoadingSuccess(isRefreshing, questions);
                        }, error -> {
                            onLoadingFinish(isPageLoading, isRefreshing);
                            onLoadingFailed(error);
                        }), 2000);


    }

    private void onLoadingFinish(boolean isPageLoading, boolean isRefreshing) {
        mIsInLoading = false;
        hideProgress(isPageLoading, isRefreshing);
    }

    private void onLoadingSuccess(boolean isRefreshing, QuestionsList questions) {

        List<QuestionItem> resultsItems = questions.getItems();
        List<Question> showingsList = QuestionMapper.fromResultsItemToTasks(resultsItems);

        boolean maybeMore = showingsList.size() >= API.PAGE_SIZE;
        if (isRefreshing) {
            getViewState().refreshRepositories(showingsList, maybeMore);
        } else {
            getViewState().addRepositories(showingsList, maybeMore);
        }

        getViewState().activateLastItemViewListener();
    }

    private void onLoadingFailed(Throwable error) {
        getViewState().hideProgressBar();
        getViewState().showError(error.toString());
    }

    public void onErrorCancel() {
        getViewState().hideError();
    }

    private void showProgress(boolean isPageLoading, boolean isRefreshing) {
        if (isPageLoading) {
            return;
        }

        if (isRefreshing) {
            getViewState().hideProgressBar();
            getViewState().showRefreshing();
        } else {
            getViewState().showListProgress();
        }
    }

    private void hideProgress(boolean isPageLoading, boolean isRefreshing) {
        if (isPageLoading) {
            return;
        }

        if (isRefreshing) {
            getViewState().hideRefreshing();
        } else {
            getViewState().hideListProgress();
        }
    }
}
