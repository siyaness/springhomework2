package com.example.springblog2.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
public class SignupRequestDto {
    @NotBlank
    @Size(min = 4, max = 10)
    @Pattern(regexp = "[a-z\\d]*${3,10}")
    private String username;

    @NotBlank
    @Size(min = 8, max = 15)
    @Pattern(regexp = "[a-zA-Z\\d]*${3,15}")
    private String password;
    private String email;
    private boolean admin = false;
    private String adminToken = "";
}
