package com.example.taskmanager.service;

import com.example.taskmanager.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getCurrentUser();
    void deleteUser(Long id);
}
