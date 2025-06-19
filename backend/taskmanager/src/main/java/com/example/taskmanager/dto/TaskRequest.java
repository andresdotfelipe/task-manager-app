package com.example.taskmanager.dto;

import com.example.taskmanager.entity.TaskStatus;

import java.time.LocalDateTime;

public record TaskRequest(
        String title,
        String description,
        TaskStatus status,
        LocalDateTime dueDate
) {}
