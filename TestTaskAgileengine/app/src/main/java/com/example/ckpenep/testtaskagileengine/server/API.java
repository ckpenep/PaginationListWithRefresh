package com.example.ckpenep.testtaskagileengine.server;

import com.example.ckpenep.testtaskagileengine.model.dto.question.QuestionsList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import io.reactivex.Observable;

/**
 * Created by ckpenep on 24.03.2018.
 */

public interface API {
    Integer PAGE_SIZE = 20;

    @GET("search?order=desc&sort=activity&site=stackoverflow")
    Observable<QuestionsList> searchQuestions(
            @Query("page") int page,
            @Query("pagesize") int pageSize,
            @Query("tagged") String order
    );
}
