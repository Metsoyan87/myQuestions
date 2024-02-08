package com.quiz.myquestions.controller.cuestionController;

import com.quiz.myquestions.entity.*;
import com.quiz.myquestions.exception.DuplicateResourceException;
import com.quiz.myquestions.repository.QuestionRepository;
import com.quiz.myquestions.repository.QuizRepository;
import com.quiz.myquestions.security.CurrentUser;
import com.quiz.myquestions.service.QuestionServiceImpl.QuestionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionServiceImpl questionServiceImpl;
    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;


    @GetMapping("/addQuestion")
    public String addUserPage() {
        return "addQuestion";
    }

    @GetMapping("/question")
    public String question() {
        return "question";
    }

    @PostMapping("/addQuestion")
    public String addQuestion(@ModelAttribute Question question, Quiz quiz, QuestionType questionType,
                              ModelMap modelmap) throws DuplicateResourceException {


        Optional<Question> findByTitle = questionServiceImpl.findByTitle(question.getTitle());
        if (findByTitle.isPresent()) {
            modelmap.addAttribute("errorMessageTitle", "Title already in use");
        }
        question.setQuiz_id(quizRepository.findById(quiz.getId()));
        question.setQuestionType(questionType);
        questionServiceImpl.saveQuestion(question);
        return "redirect:/admin";
    }

    @GetMapping("/question")
    public String allQuestion(@RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size,
                              ModelMap modelMap,
                              @AuthenticationPrincipal CurrentUser currentUser) {


        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Question> byQuestionType = questionRepository.findAll(PageRequest.of(currentPage - 1, pageSize));

        modelMap.addAttribute("questions", byQuestionType);

        int totalPages = byQuestionType.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }
        return "question";
    }

}
