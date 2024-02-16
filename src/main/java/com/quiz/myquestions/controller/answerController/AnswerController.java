package com.quiz.myquestions.controller.answerController;

import com.quiz.myquestions.entity.Answer;
import com.quiz.myquestions.exception.DuplicateResourceException;
import com.quiz.myquestions.service.answerServiceImpl.AnswerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerServiceImpl answerServiceImpl;
    @GetMapping("/addAnswer")
    public String addAnswer(ModelMap modelMap) {
        List<Answer> answer = answerServiceImpl.findAll();
        modelMap.addAttribute("answers", answer);
        return "addAnswer";
    }

    @PostMapping("/addAnswer")
    public String addAnswer(@ModelAttribute Answer answer) {
        answerServiceImpl.saveAnswer(answer);
        return "redirect:/answer";
    }
    @GetMapping("/answer")
    public String allAnswers(ModelMap modelMap) {
        List<Answer> answer = answerServiceImpl.findAll();
        modelMap.addAttribute("answers", answer);
        return "answer";
    }
}
