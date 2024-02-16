package com.quiz.myquestions.controller.questionOptionController;

import com.quiz.myquestions.entity.Question;
import com.quiz.myquestions.entity.QuestionOption;
import com.quiz.myquestions.service.QuestionOptionServiceImpl.QuestionOptionServiceImpl;
import com.quiz.myquestions.service.QuestionServiceImpl.QuestionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuestionOptionController {

    private final QuestionOptionServiceImpl questionOptionServiceImpl;
    private final QuestionServiceImpl questionServiceImpl;


    @GetMapping("/addQuestionOption")
    public String addQuestionOption(ModelMap modelMap) {
        List<Question> question = questionServiceImpl.findAll();
        modelMap.addAttribute("questions", question);
        return "addQuestionOption";
    }

    @PostMapping("/addQuestionOption")
    public String addQuestionOption(@ModelAttribute QuestionOption questionOption){
        questionOptionServiceImpl.saveQuestionOption(questionOption);
        return "redirect:/admin";
    }

    @GetMapping("/questionOption")
    public String allQuestionOption(ModelMap modelMap) {
        List<QuestionOption> questionOption = questionOptionServiceImpl.findAll();
        modelMap.addAttribute("questionOptions", questionOption);
        return "questionOption";
    }

}

