package com.quiz.myquestions.service.answerServiceImpl;

import com.quiz.myquestions.dto.answerDto.EditAnswerDto;
import com.quiz.myquestions.entity.Answer;
import com.quiz.myquestions.exception.DuplicateResourceException;
import com.quiz.myquestions.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;


    @Override
    public Page<Answer> findByAnswerById(Answer answer, Pageable pageable) {
        return answerRepository.findAnswerById(answer.getId(), pageable);
    }



    @Override
    public void saveAnswer(Answer answer) throws DuplicateResourceException {

    }

    @Override
    public void deleteAnswerById(int id) {

    }

    @Override
    public void editAnswerById(int id, EditAnswerDto dto) {

    }
}
