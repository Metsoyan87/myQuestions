package com.quiz.myquestions.controller;

import com.quiz.myquestions.entity.User;
import com.quiz.myquestions.entity.UserType;
import com.quiz.myquestions.security.CurrentUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping("/")
    public String main() {
        return "index";
    }

    @GetMapping("/loginPage")
    public String customLogin() {
        return "loginPage";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping("/quiz")
    public String quiz() {
        return "quiz";
    }

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }

    @GetMapping("/customSuccessLogin")
    public String customSuccessLogin(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            User user = currentUser.getUser();
            if (user.getUserType() == UserType.TEACHER) {
                return "redirect:/admin";
            } else if (user.getUserType() == UserType.STUDENT) {
                return "redirect:/quizs";
            }
        }
        return "redirect:/quizs";
    }


}
