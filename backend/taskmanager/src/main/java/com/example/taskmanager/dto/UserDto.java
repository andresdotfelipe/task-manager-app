package com.example.taskmanager.dto;

import com.example.taskmanager.entity.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private UserRole role;
}
