package com.quiz.myquestions.config;

import com.quiz.myquestions.entity.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//        return http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
//                .requestMatchers("/").permitAll()
//                .requestMatchers("/loginPage").permitAll()
//                .requestMatchers("/addUser").permitAll()
//                .requestMatchers("/addQuestion").hasAuthority(UserType.TEACHER.name())
//                .requestMatchers("/question").hasAuthority(UserType.TEACHER.name())
//                .requestMatchers("/allQuiz").hasAuthority(UserType.TEACHER.name())
//                .requestMatchers("/admin").hasAuthority(UserType.TEACHER.name())
//                .requestMatchers("/addQuiz").hasAuthority(UserType.TEACHER.name())
//                        .requestMatchers("/users").authenticated()
//                        .requestMatchers("/quiz").authenticated()
//                        .requestMatchers("/users/delete").hasAuthority(UserType.TEACHER.name())
//                        .requestMatchers("/quizs/delete").hasAuthority(UserType.TEACHER.name())
//                        .requestMatchers("/users/edit").authenticated()
//        )
//                .build();


        http.
                csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/").permitAll()
                .requestMatchers("/loginPage").permitAll()
                .requestMatchers("/addUser").permitAll()
                .requestMatchers("/addQuestion").hasAuthority(UserType.TEACHER.name())
                .requestMatchers("/question").authenticated()
                .requestMatchers("/allQuiz").hasAuthority(UserType.TEACHER.name())
                .requestMatchers("/admin").hasAuthority(UserType.TEACHER.name())
                .requestMatchers("/addQuiz").hasAuthority(UserType.TEACHER.name())

                .requestMatchers("/users").authenticated()
                .requestMatchers("/quiz").hasAuthority(UserType.TEACHER.name())
                .requestMatchers("/users/delete").hasAuthority(UserType.TEACHER.name())
                .requestMatchers("/quizs/delete").hasAuthority(UserType.TEACHER.name())
                .requestMatchers("/users/edit").authenticated()

                .anyRequest()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied")

                .and()
                .formLogin()
                .loginPage("/loginPage")
                .loginProcessingUrl("/login")
                .failureUrl("/loginPage?error=true")
                .defaultSuccessUrl("/customSuccessLogin")
                .and()
                .logout()
                .logoutSuccessUrl("/");

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }


}
