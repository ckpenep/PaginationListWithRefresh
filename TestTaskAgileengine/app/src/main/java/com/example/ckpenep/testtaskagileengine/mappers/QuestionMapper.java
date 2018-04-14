package com.example.ckpenep.testtaskagileengine.mappers;

import com.example.ckpenep.testtaskagileengine.model.Question;
import com.example.ckpenep.testtaskagileengine.model.dto.question.QuestionItem;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by ckpenep on 28.03.2018.
 */

public class QuestionMapper {
    public static List<Question> fromResultsItemToTasks(List<QuestionItem> getResultsItems) {
        return Observable.fromIterable(getResultsItems)
                .map(showDTO -> new Question(
                        showDTO.getQuestionId(),
                        showDTO.getTitle(),
                        showDTO.getIsAnswered(),
                        showDTO.getViewCount(),
                        showDTO.getAnswerCount(),
                        showDTO.getScore(),
                        showDTO.getLastActivityDate(),
                        showDTO.getCreationDate(),
                        showDTO.getLink(),
                        showDTO.getLastEditDate(),
                        showDTO.getAcceptedAnswerId(),
                        showDTO.getProtectedDate(),
                        showDTO.getTags()
                ))
                .toList()
                .toObservable()
                .blockingFirst();
    }
}
