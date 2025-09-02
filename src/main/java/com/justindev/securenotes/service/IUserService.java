package com.justindev.securenotes.service;

import com.justindev.securenotes.dto.UserAdminResponseDTO;
import com.justindev.securenotes.model.User;

import java.util.List;

public interface IUserService {

    void updateUserRole(Long userId, String roleName);

    List<User> getAllUsers();

    UserAdminResponseDTO getUserById(Long userId);

}
