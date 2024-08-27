package com.example.todoManagementService.service.implementation;

import com.example.todoManagementService.dao.AuditHistoryDao;
import com.example.todoManagementService.dao.Dao;
import com.example.todoManagementService.dao.TodoListDao;
import com.example.todoManagementService.exception.EntityAlreadyExistException;
import com.example.todoManagementService.model.*;
import com.example.todoManagementService.service.TodoListManagerService;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class TodoListManagerServiceImpl implements TodoListManagerService {

    private  Dao<TodoList,String> todoListDao;
    private  Dao<AuditHistory,String> auditHistoryDao;
    private ReentrantLock lock;

    public TodoListManagerServiceImpl( Dao<TodoList,String> todoListDao,Dao<AuditHistory,String> auditHistoryDao) {
        this.todoListDao = todoListDao;
        this.auditHistoryDao = auditHistoryDao;
    }

    @Override
    public void createTodoList(TodoList todoList) {
        try{
            for(Task task : todoList.getTasks()){
                AuditHistory auditHistory = createAuditHistory(task,Action.ADDED);
                todoList.getHistory().add(auditHistory);
            }
            todoListDao.create(todoList);
        } catch (Exception exception){
            log.error("Unable to create todolist");
        }

    }

    @Override
    public void addTaskToList(String listId, Task task) {
        try {
            TodoList list = todoListDao.get(listId);
            list.getTasks().add(task);
            AuditHistory auditHistory = createAuditHistory(task, Action.ADDED);
            list.getHistory().add(auditHistory);
            todoListDao.update(listId, list);
        } catch (Exception exception) {
            log.error("Unable to add task to todolist");
        }
    }

    @Override
    public void removeTaskFromList(String listId, String taskId) {
        lock.isLocked();
        try{
            TodoList list  =  todoListDao.get(listId);
            int index = 0;
            for(Task task : list.getTasks()){
                if(task.getTaskId().equals(taskId)){
                    break;
                }
                index++;
            }
            if(index>=list.getTasks().size()){
                log.error("Unable to get the task");
                //throw Exception
            } else{
                Task task = list.getTasks().get(index);
                list.getTasks().remove(index);
                AuditHistory auditHistory = createAuditHistory(task,Action.DELETED);
                list.getHistory().add(auditHistory);
                todoListDao.update(listId,list);
            }
        } catch (Exception exception){
            log.error("Unable to remove todolist");
        }finally {

        }
    }

    @Override
    public void completeTaskInTodoList(String listId, String taskId) {
        try{
            TodoList list  =  todoListDao.get(listId);
            int index = 0;
            for(Task task : list.getTasks()){
                if(task.getTaskId().equals(taskId)){
                    break;
                }
                index++;
            }
            if(index>=list.getTasks().size()){
                log.error("Unable to get the task");
                //throw Exception
            } else{
                Task task = list.getTasks().get(index);
                task.setCompletedDate(System.currentTimeMillis());
                task.setStatus(TaskStatus.COMPLETED);
                list.getTasks().remove(index);
                AuditHistory auditHistory = createAuditHistory(task,Action.COMPLETED);
                list.getHistory().add(auditHistory);
                todoListDao.update(listId,list);
            }
        } catch (Exception exception){
            log.error("Unable to remove todolist");
        }
    }

    private AuditHistory createAuditHistory(Task task, Action action) throws EntityAlreadyExistException {
        AuditHistory auditHistory = AuditHistory.builder().auditId(UUID.randomUUID().toString()).action(action).actionDate(System.currentTimeMillis()).taskId(task.getTaskId()).build();
        auditHistoryDao.create(auditHistory);
        return auditHistory;
    }

}
