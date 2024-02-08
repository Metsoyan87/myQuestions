package com.quiz.myquestions.controller.answerController;

import com.quiz.myquestions.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequiredArgsConstructor
public class AnswerController {

    @Autowired
    private AnswerRepository answerRepository;

    @GetMapping("/answer")
    public String allAnswers() {
        return answerRepository.findAll().toString();
    }
}
