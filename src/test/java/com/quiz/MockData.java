package com.quiz;


import com.quiz.myquestions.entity.User;
import com.quiz.myquestions.entity.UserType;

public class MockData {

    public static User getUnsavedUser(){
        return User.builder()
                .name("Name")
                .surname("Doe")
                .email("jane.doe@example.org")
                .password("1111")
                .userType(UserType.valueOf("TEACHER"))
                .build();
    }
    public static User getSavedUser(){
        return User.builder()
                .name("Name")
                .surname("Doe")
                .email("jane.doe@example.org")
                .password("1111")
                .userType(UserType.valueOf("TEACHER"))
                .build();
    }
    public static User getDisabledUser(){
        return User.builder()
                .name("Name")
                .surname("Doe")
                .email("jane.doe@example.org")
                .password("1111")
                .userType(UserType.valueOf("TEACHER"))
                .build();
    }

    public static User getEnabledUser(){
        return User.builder()
                .name("Name")
                .surname("Doe")
                .email("jane.doe@example.org")
                .password("1111")
                .userType(UserType.valueOf("TEACHER"))
                .build();
    }
}
