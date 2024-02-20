package com.quiz.myquestions.dto.UserDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditUserDto {

    @NotBlank
    @Schema(example = "example@gmail.com")
    private String email;
    @NotBlank
    @Schema(example = "Example1234.")
    private String password;
}
