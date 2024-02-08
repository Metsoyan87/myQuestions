package com.quiz.myquestions.service.answerServiceImpl;


import com.quiz.myquestions.dto.answerDto.EditAnswerDto;
import com.quiz.myquestions.entity.Answer;
import com.quiz.myquestions.entity.User;
import com.quiz.myquestions.exception.DuplicateResourceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface AnswerService {

    public Page<Answer> findByAnswerById(@NotNull Answer answer, Pageable pageable);

    public void saveAnswer(Answer answer) throws DuplicateResourceException;

    public void deleteAnswerById(int id);

    public void editAnswerById(int id, EditAnswerDto dto);
}
