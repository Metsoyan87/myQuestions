package com.quiz.myquestions.service.QuestionServiceImpl;

import com.quiz.myquestions.entity.Question;
import com.quiz.myquestions.exception.DuplicateResourceException;
import com.quiz.myquestions.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;


    @Override
    public Optional<Question> findByTitle(String title) {
        return questionRepository.findByTitle(title);
    }


    @Override
    public void saveQuestion(Question question)  {
        questionRepository.save(question);
    }

    public Question findById(int id) {
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }


}
