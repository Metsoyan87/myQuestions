package com.quiz.myquestions.converter;


import com.quiz.myquestions.dto.UserDto.CreateUserDto;
import com.quiz.myquestions.dto.UserDto.UserResponseDto;
import com.quiz.myquestions.entity.User;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;


@UtilityClass
public class UserConverter {

    public User convertDtoToEntity(CreateUserDto createUserDto) {
        User user = new User();
        user.setName(createUserDto.getName());
        user.setSurname(createUserDto.getSurname());
        user.setEmail(createUserDto.getEmail());
        return user;
    }

    public UserResponseDto convertEntityToResponseDto(User user) {

        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .build();
    }


    public List<UserResponseDto> convertEntitiesToResponseDto(List<User> all) {
        List<UserResponseDto> authorResponseDto = new ArrayList<>();
        for (User user : all) {
            authorResponseDto.add(convertEntityToResponseDto(user));

        }
        return authorResponseDto;
    }
}
