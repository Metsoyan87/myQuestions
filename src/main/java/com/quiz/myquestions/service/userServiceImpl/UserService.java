package com.quiz.myquestions.service.userServiceImpl;


import com.quiz.myquestions.dto.UserDto.EditUserDto;
import com.quiz.myquestions.entity.User;
import com.quiz.myquestions.exception.DuplicateResourceException;
import jakarta.mail.MessagingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface UserService {
    public Optional<User> findByEmail(String email);

    public void save(User user) throws DuplicateResourceException;
    public Page<User> findByUserRole(@NotNull User user, Pageable pageable);

    public void sendEmail(User user) throws MessagingException;

    public void deleteById(int id);
    public void editUser(int id, EditUserDto dto);
}
