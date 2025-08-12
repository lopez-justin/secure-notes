package com.justindev.securenotes.service.impl;

import com.justindev.securenotes.dto.UserDTO;
import com.justindev.securenotes.model.AppRole;
import com.justindev.securenotes.model.Role;
import com.justindev.securenotes.model.User;
import com.justindev.securenotes.repository.IRoleRepository;
import com.justindev.securenotes.repository.IUserRepository;
import com.justindev.securenotes.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;


    @Override
    public void updateUserRole(Long userId, String roleName) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        Role role = this.roleRepository.findByRoleName(AppRole.valueOf(roleName))
                .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName));
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public UserDTO getUserById(Long userId) {
        return this.convertToDTO(this.userRepository.findById(userId).orElseThrow());
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.isAccountNonLocked(),
                user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),
                user.isEnabled(),
                user.getCredentialsExpiryDate(),
                user.getAccountExpiryDate(),
                user.getTwoFactorSecret(),
                user.isTwoFactorEnabled(),
                user.getSignUpMethod(),
                user.getRole(),
                user.getCreatedDate(),
                user.getLastModifiedDate()
        );
    }
}
