package com.justindev.securenotes.controller;

import com.justindev.securenotes.dto.UserDTO;
import com.justindev.securenotes.model.User;
import com.justindev.securenotes.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final IUserService userService;

    @GetMapping("/get-users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/update-role")
    public ResponseEntity<String> updateUserRole(@RequestParam Long userId, @RequestParam String roleName) {
        this.userService.updateUserRole(userId, roleName);
        return new ResponseEntity<>("User role updated successfully", HttpStatus.OK);
    }

    @GetMapping("/get-user/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        UserDTO userDTO = this.userService.getUserById(userId);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

}
