package com.quiz.myquestions.controller.quizController;

import com.quiz.myquestions.entity.Quiz;
import com.quiz.myquestions.exception.DuplicateResourceException;
import com.quiz.myquestions.repository.QuizRepository;
import com.quiz.myquestions.security.CurrentUser;
import com.quiz.myquestions.service.quizServiceImpl.QuizServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequiredArgsConstructor

public class QuizController {

    private final QuizServiceImpl quizServiceImpl;

    @Autowired
    private QuizRepository quizRepository;


    @GetMapping("/addQuiz")
    public String addQuizPage() {
        return "addQuiz";
    }

    @GetMapping("/quizs/delete{id}")
    public String deleteUser(@PathVariable("id") int id) {
        quizRepository.deleteById(id);
        return "redirect:/quizs";
    }

    @PostMapping("/addQuiz")
    public String addQuiz(@ModelAttribute Quiz quiz, ModelMap modelMap) throws DuplicateResourceException {
        Optional<Quiz> title = quizServiceImpl.findQuizByTitle(quiz.getTitle());
        if (title.isPresent()) {
            modelMap.addAttribute("errorMessageTitle", "Title already in use");
            return "/addQuiz";
        }
        quizServiceImpl.saveQuiz(quiz);
        return "redirect:/admin";
    }

    @GetMapping("/quizs")
    public String allQuiz(@RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           ModelMap modelMap) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Quiz> byQuizTitle = quizRepository.findAll(PageRequest.of(currentPage - 1, pageSize));
        modelMap.addAttribute("quizi", byQuizTitle);

        int totalPages = byQuizTitle.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }

        return "quizs";
    }
}

