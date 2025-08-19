package com.justindev.securenotes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class LoginResponseDTO {

    private String username;
    private String token;
    private List<String> roles;

}
