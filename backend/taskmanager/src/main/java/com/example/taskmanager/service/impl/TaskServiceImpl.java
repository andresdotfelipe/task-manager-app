package com.example.taskmanager.service.impl;

import com.example.taskmanager.dto.TaskRequest;
import com.example.taskmanager.dto.TaskResponse;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.mapper.TaskMapper;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.UserRepository;
import com.example.taskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskResponse createTask(TaskRequest request, String userEmail) {
        User user = getUserByEmail(userEmail);
        Task task = Task.builder()
                .title(request.title())
                .description(request.description())
                .status(request.status())
                .createdAt(LocalDateTime.now())
                .dueDate(request.dueDate())
                .user(user)
                .build();
        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public List<TaskResponse> getUserTasks(String userEmail) {
        User user = getUserByEmail(userEmail);
        return taskMapper.toResponseList(taskRepository.findByUserId(user.getId()));
    }

    @Override
    public TaskResponse getTaskById(Long taskId, String userEmail) {
        return taskMapper.toDto(getTaskByIdAndUser(taskId, userEmail));
    }

    @Override
    public TaskResponse updateTask(Long taskId, TaskRequest request, String userEmail) {
        Task task = getTaskByIdAndUser(taskId, userEmail);
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(request.status());
        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public void deleteTask(Long taskId, String userEmail) {
        Task task = getTaskByIdAndUser(taskId, userEmail);
        taskRepository.delete(task);
    }

    private Task getTaskByIdAndUser(Long taskId, String userEmail) {
        User user = getUserByEmail(userEmail);
        return taskRepository.findById(taskId)
                .filter(task -> task.getUser().getId().equals(user.getId()))
                .orElseThrow(() -> new NoSuchElementException("Task not found or not authorized"));
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
