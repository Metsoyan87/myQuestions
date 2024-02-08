package com.quiz.myquestions.service.userServiceImpl;

import com.quiz.myquestions.dto.UserDto.EditUserDto;
import com.quiz.myquestions.entity.User;
import com.quiz.myquestions.entity.UserType;
import com.quiz.myquestions.exception.DuplicateResourceException;
import com.quiz.myquestions.repository.UserRepository;
import com.quiz.myquestions.service.mailService.MailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;


    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }



    public Page<User> findByUserRole(@NotNull User user, Pageable pageable) {
        return userRepository.findUsersById(user.getId(), pageable);
    }

    public void save(User user) throws DuplicateResourceException {
        if (userRepository.findByEmail(user.getName()).isPresent()) {
            throw new DuplicateResourceException("User already exists");
        }
        userRepository.save(user);
    }

    @Override
    public void sendEmail(User user) throws MessagingException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserType(UserType.STUDENT);
        userRepository.save(user);

        mailService.sendHtmlEmail(user.getEmail(), "Please verify your email",
                "Hi " + user.getName() + "\n" +
                        "Please verify your account by clicking on this link " +
                        "<a href=\"http://localhost:8080/user/verify?email=" +
                        user.getEmail());


    }

    @Override
    public void editUser(int id, EditUserDto editUserDto) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            throw new IllegalStateException("User not found");
        }
        User user = optional.get();
        edit(user, editUserDto);
    }

    private void edit(User user, @NotNull EditUserDto editUserDto) {

        String email = editUserDto.getEmail();
        String password = editUserDto.getPassword();

        if (StringUtils.hasText(email)) {
            user.setEmail(email);
        }
        if (StringUtils.hasText(password)) {
            user.setPassword(passwordEncoder.encode(password));
        }
        userRepository.save(user);
    }
}






