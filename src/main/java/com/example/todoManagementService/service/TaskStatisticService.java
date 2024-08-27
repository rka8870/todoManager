package com.example.todoManagementService.service;

import com.example.todoManagementService.model.AuditHistory;
import com.example.todoManagementService.model.Filter;
import com.example.todoManagementService.model.Sort;

import java.util.List;

public interface TaskStatisticService {

    List<AuditHistory> getActivityLog (Filter filter, Sort sort);


}
