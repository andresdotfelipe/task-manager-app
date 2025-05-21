package com.example.taskmanager.service;

import com.example.taskmanager.dto.TaskRequest;
import com.example.taskmanager.dto.TaskResponse;

import java.util.List;

public interface TaskService {
    TaskResponse createTask(TaskRequest request, String userEmail);
    List<TaskResponse> getTasksForUser(String userEmail);
    TaskResponse updateTask(Long id, TaskRequest request, String userEmail);
    void deleteTask(Long id, String userEmail);
}
