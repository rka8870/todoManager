package com.example.todoManagementService.model;


import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class AuditHistory
{
    private String auditId;
    private String taskId;
    private Action action;
    private long actionDate;
}
