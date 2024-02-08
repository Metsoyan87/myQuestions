package com.quiz.myquestions.dto.UserDto;

import com.quiz.myquestions.entity.UserType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {

    @NotBlank(message = "Name is empty")
    @Size(min = 3, max = 10, message = "Check your name")
    private String name;

    @NotBlank(message = "Surname is empty")
    @Size(min = 3, max = 10, message = "Check your surname")
    private String surname;

    @Email
    @NotBlank(message = "email is empty")
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            message = "email address is empty")
    private String email;

    @NotBlank(message = "You forgot to create a password")
    @Size(min = 6, message = "password should be minimum 6 characters")
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;


}
