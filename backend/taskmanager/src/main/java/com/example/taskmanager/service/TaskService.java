package com.example.taskmanager.service;

import com.example.taskmanager.dto.TaskRequest;
import com.example.taskmanager.dto.TaskResponse;

import java.util.List;

public interface TaskService {
    TaskResponse createTask(TaskRequest request, String userEmail);
    List<TaskResponse> getUserTasks(String userEmail);
    TaskResponse getTaskById(Long taskId, String userEmail);
    TaskResponse updateTask(Long taskId, TaskRequest request, String userEmail);
    void deleteTask(Long taskId, String userEmail);
    List<TaskResponse> filterTasks(String userEmail, String search, String status, String sort);
}
