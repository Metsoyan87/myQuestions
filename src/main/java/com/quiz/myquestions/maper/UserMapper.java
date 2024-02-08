package com.quiz.myquestions.maper;



import com.quiz.myquestions.dto.UserDto.CreateUserDto;
import com.quiz.myquestions.dto.UserDto.UserDto;
import com.quiz.myquestions.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {


    User map(CreateUserDto createUserDto);

    List<UserDto> map(List<User> users);

    UserDto map(User user);
}
