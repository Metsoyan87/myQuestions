package com.quiz.myquestions.service.QuestionServiceImpl;

import com.quiz.myquestions.entity.Question;
import com.quiz.myquestions.exception.DuplicateResourceException;

import java.util.List;
import java.util.Optional;

public interface QuestionService {

    public Optional<Question> findByTitle(String title);

    public void saveQuestion(Question question) throws DuplicateResourceException;



}
