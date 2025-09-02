package com.justindev.securenotes.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class SignupRequestDTO {

    @NotBlank
    @Size(min = 3, max = 30)
    private String username;

    @Email
    @NotBlank
    @Size(min = 8, max = 50)
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

}
