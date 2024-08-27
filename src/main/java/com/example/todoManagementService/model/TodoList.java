package com.example.todoManagementService.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TodoList {
    private String todoListId;
    private String userId;
    private String name;
    private List<Task> tasks = new ArrayList<>();
    private List<AuditHistory> history = new ArrayList<>();
}
