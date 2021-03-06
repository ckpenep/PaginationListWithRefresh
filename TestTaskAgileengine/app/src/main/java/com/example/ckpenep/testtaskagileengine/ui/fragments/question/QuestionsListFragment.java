package com.example.ckpenep.testtaskagileengine.ui.fragments.question;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.ckpenep.testtaskagileengine.R;
import com.example.ckpenep.testtaskagileengine.adapter.QuestionAdapter;
import com.example.ckpenep.testtaskagileengine.adapter.QuestionListAdapter;
import com.example.ckpenep.testtaskagileengine.model.Question;
import com.example.ckpenep.testtaskagileengine.utils.FrameSwipeRefreshLayout;

import java.util.List;

/**
 * Created by ckpenep on 26.03.2018.
 */

public class QuestionsListFragment extends MvpAppCompatFragment implements QuestionView, QuestionAdapter.OnScrollToBottomListener {

    @InjectPresenter
    QuestionPresenter mPresenter;

    protected View mFooter;
    FrameSwipeRefreshLayout mSwipeRefreshLayout;
    ProgressBar progressBar;
    TextView noQuestionsTextView;
    RecyclerView mRecyclerView;

    private AlertDialog mErrorDialog;
    private LinearLayoutManager layoutManager;
    private QuestionListAdapter mAdapter;

    public static QuestionsListFragment newInstance() {
        QuestionsListFragment fragment = new QuestionsListFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_staroverflow, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.questions_list_view);
        mSwipeRefreshLayout = (FrameSwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        noQuestionsTextView = (TextView) view.findViewById(R.id.text_view_no_questions);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initList();

        // SwipeRefreshLayout
        mSwipeRefreshLayout.setOnRefreshListener(() -> mPresenter.loadRepositories(true));
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark);

        mFooter = getLayoutInflater(savedInstanceState).inflate(R.layout.loading_item, mRecyclerView, false);
    }

    @Override
    public void onDestroy() {
        if (mErrorDialog != null && mErrorDialog.isShowing()) {
            mErrorDialog.setOnCancelListener(null);
            mErrorDialog.dismiss();
        }

        super.onDestroy();
    }

    protected void initList() {
        mAdapter = new QuestionListAdapter();
        layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onScrollToBottom() {
        mPresenter.loadNextRepositories(layoutManager.getItemCount());
    }

    @Override
    public void showRefreshing() {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(true));
    }

    @Override
    public void hideRefreshing() {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(false));
    }

    @Override
    public void showListProgress() {
        mRecyclerView.setVisibility(View.GONE);
        noQuestionsTextView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideListProgress() {
        mRecyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void refreshRepositories(List<Question> questions, boolean maybeMore) {
        mAdapter.refreshAll(questions);
        if (mAdapter.getFootersCount() == 0)
            mAdapter.addFooter(mFooter);
    }

    @Override
    public void addRepositories(List<Question> questions, boolean maybeMore) {
        mAdapter.addAll(questions);
        if (mAdapter.getFootersCount() == 0)
            mAdapter.addFooter(mFooter);
    }

    @Override
    public void activateLastItemViewListener() {
        enableSearchOnFinish();
    }

    @Override
    public void disableLastItemViewListener() {
        disableSearchOnFinish();
    }

    private void enableSearchOnFinish() {
        mRecyclerView.addOnScrollListener(new FinishScrollListener());
    }

    private void disableSearchOnFinish() {
        mRecyclerView.clearOnScrollListeners();
    }

    private class FinishScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition() + 1;
            int modelsCount = mAdapter.getItemCount();
           //boolean isRefresh = mAdapter.

            if (lastVisibleItemPosition == modelsCount && modelsCount > 10 ) {
                mPresenter.loadNextRepositories(layoutManager.getItemCount());
            }
        }
    }

    @Override
    public void hideProgressBar() {
        new Handler().postDelayed(() ->
                mAdapter.removeFooter(mFooter), 10);
    }

    @Override
    public void showError(String message) {
        mErrorDialog = new AlertDialog.Builder(getContext())
                .setTitle(R.string.app_name)
                .setMessage(message)
                .setOnCancelListener(dialog -> mPresenter.onErrorCancel())
                .show();
    }

    @Override
    public void hideError() {
        if (mErrorDialog != null && mErrorDialog.isShowing()) {
            mErrorDialog.hide();
        }
    }
}
