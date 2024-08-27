package com.example.todoManagementService.service;

import com.example.todoManagementService.model.Task;

public interface TaskService {

    void createTask(Task task);
    void updateTask(Task task);
    void deleteTask(String taskId);
    Task getTask(String taskId);

}
