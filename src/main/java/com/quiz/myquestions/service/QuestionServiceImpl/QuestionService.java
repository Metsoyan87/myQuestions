package com.quiz.myquestions.service.QuestionServiceImpl;

import com.quiz.myquestions.entity.Question;
import com.quiz.myquestions.entity.Quiz;
import com.quiz.myquestions.exception.DuplicateResourceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface QuestionService {

    Optional<Question> findByTitle(String title);

    void saveQuestion(Question question) throws DuplicateResourceException;

    public Question findById(int id);
}
