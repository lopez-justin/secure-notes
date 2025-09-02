package com.justindev.securenotes.controller;

import com.justindev.securenotes.dto.*;
import com.justindev.securenotes.security.jwt.JWTUtils;
import com.justindev.securenotes.service.IAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;

    private final IAuthService authService;

    @PostMapping("/public/signin")
    public ResponseEntity<LoginResponseDTO> signin(@RequestBody LoginRequestDTO loginRequestDTO) {
        return new ResponseEntity<>(this.authService.authenticateUser(loginRequestDTO), HttpStatus.OK);
    }

    @PostMapping("/public/signup")
    public ResponseEntity<SignupResponseDTO> signup(@RequestBody @Valid SignupRequestDTO signupRequestDTO) {
        return new ResponseEntity<>(this.authService.registerUser(signupRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<UserInfoResponseDTO> getUserDetails(@AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(this.authService.getUserInfo(userDetails), HttpStatus.OK);
    }

}
