package com.example.todoManagementService.service;

import com.example.todoManagementService.model.Task;
import com.example.todoManagementService.model.TodoList;

public interface TodoListManagerService {

    void createTodoList(TodoList todoList);

    void addTaskToList(String listId, Task task);

    void removeTaskFromList(String listId, String TaskId);

    void completeTaskInTodoList(String listId, String TaskId);


}
