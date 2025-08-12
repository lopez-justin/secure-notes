package com.justindev.securenotes.service;

import com.justindev.securenotes.dto.UserDTO;
import com.justindev.securenotes.model.User;

import java.util.List;

public interface IUserService {

    void updateUserRole(Long userId, String roleName);

    List<User> getAllUsers();

    UserDTO getUserById(Long userId);

}
