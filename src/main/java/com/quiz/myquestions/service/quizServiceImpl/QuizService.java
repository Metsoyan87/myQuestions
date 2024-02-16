package com.quiz.myquestions.service.quizServiceImpl;

import com.quiz.myquestions.entity.Answer;
import com.quiz.myquestions.entity.Quiz;
import com.quiz.myquestions.entity.User;
import com.quiz.myquestions.exception.DuplicateResourceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface QuizService {
    public Page<Quiz> findQuizById(@NotNull Quiz quiz, Pageable pageable);

    public Optional<Quiz> findQuizByTitle(String title);

    public List<Quiz> findAllQuizs();

    public void saveQuiz(Quiz quiz) throws DuplicateResourceException;

    public void deleteById(int id);

    List<Quiz> findAll();
}
