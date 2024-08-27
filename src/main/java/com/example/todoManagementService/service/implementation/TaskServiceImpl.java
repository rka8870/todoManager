package com.example.todoManagementService.service.implementation;

import com.example.todoManagementService.dao.Dao;
import com.example.todoManagementService.dao.TaskDao;
import com.example.todoManagementService.model.Task;
import com.example.todoManagementService.service.TaskService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaskServiceImpl implements TaskService {

    private Dao<Task,String> taskDao;

    public TaskServiceImpl(Dao<Task,String> taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public void createTask(Task task) {
        try{
            taskDao.create(task);
        } catch (Exception exception){
            log.error("Unable to create task");
        }

    }

    @Override
    public void updateTask(Task task) {
        try{
            taskDao.update(task.getTaskId(),task);
        } catch (Exception exception){
            log.error("Unable to update task");
        }
    }

    @Override
    public void deleteTask(String taskId) {
        try{
            taskDao.delete(taskId);
        } catch (Exception exception){
            log.error("Unable to delete task");
        }
    }

    @Override
    public Task getTask(String taskId) {
        try{
            return taskDao.get(taskId);
        } catch (Exception exception){
            log.error("Unable to get task");
        }
        return null;
    }
}
