package com.quiz.myquestions.service.quizServiceImpl;
import com.quiz.myquestions.entity.Quiz;
import com.quiz.myquestions.exception.DuplicateResourceException;
import com.quiz.myquestions.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    @Override
    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }
    @Override
    public Page<Quiz> findQuizById(Quiz quiz, Pageable pageable) {
        return quizRepository.findQuizById(quiz.getId(), pageable);
    }

    @Override
    public Optional<Quiz> findQuizByTitle(String title) {
        return quizRepository.findByTitle(title);
    }

    @Override
    public List<Quiz> findAllQuizs() {
        return quizRepository.findAll();
    }

    @Override
    public void saveQuiz(Quiz quiz) throws DuplicateResourceException {
        if (quizRepository.findByTitle(quiz.getTitle()).isPresent()) {
            throw new DuplicateResourceException("Quiz exists");
        }
        quizRepository.save(quiz);
    }

    @Override
    public void deleteById(int id) {
        quizRepository.deleteById(id);
    }

}
