package com.quiz.myquestions.controller.userController;


import com.quiz.myquestions.dto.UserDto.EditUserDto;
import com.quiz.myquestions.entity.User;
import com.quiz.myquestions.entity.UserType;
import com.quiz.myquestions.exception.DuplicateResourceException;
import com.quiz.myquestions.security.CurrentUser;
import com.quiz.myquestions.service.userServiceImpl.UserServiceImpl;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequiredArgsConstructor


public class UserController {
    private final UserServiceImpl userServiceImpl;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/addUser")
    public String addUserPage() {
        return "addUser";
    }

    @GetMapping("/users/delete{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userServiceImpl.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/users/edit")
    public String editUser(@RequestParam("userId") int id, ModelMap modelMap) {
        modelMap.addAttribute("userId", id);
        return "editUsers";
    }

    @PostMapping("/users/edit/{id}")
    public String editUser(@PathVariable int id,
                           @ModelAttribute EditUserDto editUserDto,
                           ModelMap modelMap) {
        try {
            userServiceImpl.editUser(id, editUserDto);
            return "redirect:/users";
        } catch (IllegalStateException ex) {
            modelMap.addAttribute("errorMessage", ex.getMessage());
            return "users";
        }
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user, ModelMap modelMap) throws IOException, MessagingException, DuplicateResourceException {
        Optional<User> byEmail = userServiceImpl.findByEmail(user.getEmail());
        if (byEmail.isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setUserType(UserType.STUDENT);
            userServiceImpl.save(user);
            return "redirect:/loginPage";
        } else {
            return "redirect:/user/register?msg=Email already in use";
        }
    }

    @GetMapping("/users")
    public String users(@RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size,
                        ModelMap modelMap,
                        @AuthenticationPrincipal CurrentUser currentUser) {


        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<User> byUserRole = userServiceImpl.findByUserRole(currentUser.getUser(),
                PageRequest.of(currentPage - 1, pageSize));

        modelMap.addAttribute("users", byUserRole);

        int totalPages = byUserRole.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }
        return "users";
    }

}
