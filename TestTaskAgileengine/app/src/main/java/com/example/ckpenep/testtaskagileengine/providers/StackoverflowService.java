package com.example.ckpenep.testtaskagileengine.providers;

import com.example.ckpenep.testtaskagileengine.model.dto.question.QuestionsList;
import com.example.ckpenep.testtaskagileengine.server.API;

import io.reactivex.Observable;

/**
 * Created by ckpenep on 28.03.2018.
 */

public class StackoverflowService {
    private API api;

    public StackoverflowService(API mApi) {
        api = mApi;
    }

    public Observable<QuestionsList> getUserRepos(int page, int pagesize, String order) {
        return api.searchQuestions(page, pagesize, order);
    }
}
