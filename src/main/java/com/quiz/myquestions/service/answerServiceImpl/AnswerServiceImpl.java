package com.quiz.myquestions.service.answerServiceImpl;

import com.quiz.myquestions.dto.answerDto.EditAnswerDto;
import com.quiz.myquestions.entity.Answer;
import com.quiz.myquestions.exception.DuplicateResourceException;
import com.quiz.myquestions.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;


    @Override
    public Page<Answer> findByAnswerById(Answer answer, Pageable pageable) {
        return null;
    }

    @Override
    public void saveAnswer(Answer answer)  {

    }

    @Override
    public void deleteAnswerById(int id) {

    }

    @Override
    public void editAnswerById(int id, EditAnswerDto dto) {

    }

    @Override
    public List<Answer> findAll() {
        return answerRepository.findAll();
    }
}
