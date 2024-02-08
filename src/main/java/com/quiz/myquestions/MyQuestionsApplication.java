package com.quiz.myquestions;

import com.quiz.myquestions.entity.User;
import com.quiz.myquestions.entity.UserType;
import com.quiz.myquestions.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Optional;


@EnableAsync
@SpringBootApplication
public class MyQuestionsApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(MyQuestionsApplication.class, args);
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void run(String... args) throws Exception {
        Optional<User> byEmail = userRepository.findByEmail("admin@mail.com");

        if (byEmail.isEmpty()) {
            userRepository.save(User.builder()
                    .name("admin")
                    .surname("admin")
                    .email("admin@mail.com")
                    .password(passwordEncoder().encode("admin"))
                    .userType(UserType.TEACHER)
                    .build());
        }
    }
}
