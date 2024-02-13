package com.quiz.myquestions.controller.cuestionController;

import com.quiz.myquestions.entity.Question;
import com.quiz.myquestions.entity.Quiz;
import com.quiz.myquestions.repository.QuestionRepository;
import com.quiz.myquestions.service.QuestionServiceImpl.QuestionServiceImpl;
import com.quiz.myquestions.service.quizServiceImpl.QuizServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionServiceImpl questionServiceImpl;
    private final QuestionRepository questionRepository;
    private final QuizServiceImpl quizServiceImpl;


    @GetMapping("/addQuestion")
    public String addUserPage(ModelMap modelMap) {
        List<Quiz> quiz = quizServiceImpl.findAllQuizs();
        modelMap.addAttribute("quizi", quiz);
        return "addQuestion";
    }


    @PostMapping("/addQuestion")
    public String addQuestion(@ModelAttribute Question question) {
        questionServiceImpl.saveQuestion(question);
        return "redirect:/question";
    }
    @GetMapping ("/question")
    public String questionPage(ModelMap modelMap){
        List <Question> questions = questionRepository.findAll();
        modelMap.addAttribute("questions", questions);
        return "question";
    }
}