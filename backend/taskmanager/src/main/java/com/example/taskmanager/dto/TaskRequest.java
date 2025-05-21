package com.example.taskmanager.dto;

public record TaskRequest(
        String title,
        String description,
        boolean completed
) {}
