package com.quiz.myquestions.controller;

import com.quiz.myquestions.entity.User;
import com.quiz.myquestions.repository.UserRepository;
import com.quiz.myquestions.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin")
    public String allUsers(@RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           ModelMap modelMap,
                           @AuthenticationPrincipal CurrentUser currentUser) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<User> byUserRole = userRepository.findAll(PageRequest.of(currentPage - 1, pageSize));

        modelMap.addAttribute("users", byUserRole);

        int totalPages = byUserRole.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }

        return "admin";
    }













// protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        // Получаем информацию о пользователе, например, из сессии или базы данных
//        String userType = getUserType(request);
//
//        // Проверяем, является ли пользователь администратором
//        if ("admin".equals(userType)) {
//            // Если да, выполняем редирект на страницу админки
//            response.sendRedirect("/admin");
//        } else {
//            // Если нет, можно выполнить другие действия или редирект на другую страницу
//            response.sendRedirect("/");
//        }
//    }
//
//    // Метод для получения типа пользователя (может потребоваться реализация)
//    private String getUserType(HttpServletRequest request) {
//        // Здесь можно использовать сессии или обращение к базе данных
//        // В данном примере всегда возвращается строка "admin" для упрощения
//        return "admin";
//    }
}
