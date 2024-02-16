package com.quiz.myquestions.service.QuestionOptionServiceImpl;

import com.quiz.myquestions.entity.QuestionOption;
import com.quiz.myquestions.repository.QuestionOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionOptionServiceImpl implements QuestionOptionService {

    private final QuestionOptionRepository questionOptionRepository;

    @Override
    public Optional<QuestionOption> findByTitle(String title) {
        return questionOptionRepository.findByTitle(title);
    }


    @Override
    public void saveQuestionOption(QuestionOption questionOption) {
        questionOptionRepository.save(questionOption);

    }

    @Override
    public List<QuestionOption> findAll() {
        return questionOptionRepository.findAll();
    }


}
