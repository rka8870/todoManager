package com.example.todoManagementService.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class Task {
    private String taskId;
    private Tag tag;
    private long tobyDate;
    private long completedDate;
    private String userId;
    private TaskStatus status;
    private List<Task> prlist;
}
