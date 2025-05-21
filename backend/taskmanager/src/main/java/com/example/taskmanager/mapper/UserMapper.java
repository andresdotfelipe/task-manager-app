package com.example.taskmanager.mapper;

import com.example.taskmanager.dto.UserDto;
import com.example.taskmanager.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
}
