package com.quiz.myquestions.controller.questionOptionController;


import com.quiz.myquestions.entity.QuestionOption;
import com.quiz.myquestions.exception.DuplicateResourceException;
import com.quiz.myquestions.repository.QuestionOptionRepository;
import com.quiz.myquestions.security.CurrentUser;
import com.quiz.myquestions.service.QuestionOptionServiceImpl.QuestionOptionServiceImpl;
import jakarta.mail.MessagingException;
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

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class QuestionOptionController {

    private final QuestionOptionServiceImpl questionOptionServiceImpl;

    private QuestionOptionRepository questionOptionRepository;

    @GetMapping("/addQuestionOption")
    public String addUserPage() {
        return "addQuestionOption";
    }

    @PostMapping("/addQuestionOption")
    public String addUser(@ModelAttribute QuestionOption questionOption, ModelMap modelMap) throws IOException, MessagingException, DuplicateResourceException {
        Optional<QuestionOption> byTitle = questionOptionServiceImpl.findByTitle(questionOption.getTitle());
        if (byTitle.isPresent()) {
            modelMap.addAttribute("errorMessageTitle", "Title already in use");
            questionOptionServiceImpl.saveQuestionOption(questionOption);
            return "/admin";
        }
        return "redirect:/admin";
    }
    @GetMapping("/questionOptionTitle")
    public String allUsers(@RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           ModelMap modelMap,
                           @AuthenticationPrincipal CurrentUser currentUser) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<QuestionOption> byQuestionOptionTitle = questionOptionRepository.findAll(PageRequest.of(currentPage - 1, pageSize));

        modelMap.addAttribute("quizi", byQuestionOptionTitle);

        int totalPages = byQuestionOptionTitle.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }

        return "questionOptionTitle";
    }
}

