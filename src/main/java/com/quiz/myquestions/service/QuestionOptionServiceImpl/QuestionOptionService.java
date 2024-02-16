package com.quiz.myquestions.service.QuestionOptionServiceImpl;

import com.quiz.myquestions.entity.QuestionOption;
import com.quiz.myquestions.exception.DuplicateResourceException;

import java.util.List;
import java.util.Optional;

public interface QuestionOptionService {

    public Optional<QuestionOption> findByTitle(String title);

    void saveQuestionOption(QuestionOption questionOption) throws DuplicateResourceException;


    List<QuestionOption> findAll();
}
