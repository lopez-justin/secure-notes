package com.justindev.securenotes.service;

import com.justindev.securenotes.dto.UserAdminResponseDTO;
import com.justindev.securenotes.model.User;

import java.util.List;

public interface IUserService {

    void updateUserRole(Long userId, String roleName);

    List<User> getAllUsers();

    UserAdminResponseDTO getUserById(Long userId);

    void updatePassword(Long userId, String password);

    void updateAccountLockStatus(Long userId, boolean lock);

    void updateAccountExpiryStatus(Long userId, boolean expire);

    void updateAccountEnabledStatus(Long userId, boolean enabled);

    void updateCredentialsExpiryStatus(Long userId, boolean expire);

}
