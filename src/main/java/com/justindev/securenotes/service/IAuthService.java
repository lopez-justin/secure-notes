package com.justindev.securenotes.service;

import com.justindev.securenotes.dto.*;
import org.springframework.security.core.userdetails.UserDetails;

public interface IAuthService {

    LoginResponseDTO authenticateUser(LoginRequestDTO loginRequestDTO);

    SignupResponseDTO registerUser(SignupRequestDTO signupRequestDTO);

    UserInfoResponseDTO getUserInfo(UserDetails userDetails);

}
