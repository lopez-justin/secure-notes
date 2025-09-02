package com.justindev.securenotes.service.impl;

import com.justindev.securenotes.dto.*;
import com.justindev.securenotes.model.AppRole;
import com.justindev.securenotes.model.Role;
import com.justindev.securenotes.model.User;
import com.justindev.securenotes.repository.IRoleRepository;
import com.justindev.securenotes.repository.IUserRepository;
import com.justindev.securenotes.security.jwt.JWTUtils;
import com.justindev.securenotes.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;
    private final PasswordEncoder encoder;

    @Override
    public LoginResponseDTO authenticateUser(LoginRequestDTO loginRequestDTO) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(e.getMessage());
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .toList();

        return new LoginResponseDTO(userDetails.getUsername(), jwtToken, roles);
    }

    @Override
    public SignupResponseDTO registerUser(SignupRequestDTO signupRequestDTO) {
        if (this.userRepository.existsByUsername(signupRequestDTO.getUsername())) {
            return new SignupResponseDTO("Error: Username is already taken!");
        }

        if (this.userRepository.existsByEmail(signupRequestDTO.getEmail())) {
            return new SignupResponseDTO("Error: Email is already in use!");
        }

        // Create new user's account
        User user = new User(
                signupRequestDTO.getUsername(),
                signupRequestDTO.getEmail(),
                encoder.encode(signupRequestDTO.getPassword())
        );

        Set<String> rolesSet = signupRequestDTO.getRole();
        Role role;
        if (rolesSet == null || rolesSet.isEmpty()) {
            role = this.roleRepository.findByRoleName(AppRole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
        } else {
            String roleString = rolesSet.iterator().next();
            if (roleString.equals("admin")) {
                role = this.roleRepository.findByRoleName(AppRole.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            } else {
                role = this.roleRepository.findByRoleName(AppRole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            }
        }
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setCredentialsExpiryDate(LocalDate.now().plusYears(1));
        user.setAccountExpiryDate(LocalDate.now().plusYears(1));
        user.setTwoFactorEnabled(false);
        user.setSignUpMethod("email");
        user.setRole(role);
        this.userRepository.save(user);

        return new SignupResponseDTO("User registered successfully!");
    }

    @Override
    public UserInfoResponseDTO getUserInfo(UserDetails userDetails) {
        User user = this.userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found with username: " + userDetails.getUsername()));

        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(role -> role.getAuthority())
                .toList();

        return new UserInfoResponseDTO(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.isAccountNonLocked(),
                user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),
                user.isEnabled(),
                user.getCredentialsExpiryDate(),
                user.getAccountExpiryDate(),
                user.isTwoFactorEnabled(),
                roles
        );
    }
}
