package com.example.todoManagementService.driver;

import com.example.todoManagementService.dao.AuditHistoryDao;
import com.example.todoManagementService.dao.Dao;
import com.example.todoManagementService.dao.TaskDao;
import com.example.todoManagementService.dao.TodoListDao;
import com.example.todoManagementService.exception.EntityNotFoundException;
import com.example.todoManagementService.model.*;
import com.example.todoManagementService.service.TaskService;
import com.example.todoManagementService.service.TodoListManagerService;
import com.example.todoManagementService.service.implementation.TaskServiceImpl;
import com.example.todoManagementService.service.implementation.TodoListManagerServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class DriverTodoList {

    public static void main(String[] args) throws EntityNotFoundException {
        // Mock DAOs
        Dao<Task,String> taskDao = new TaskDao();
        Dao<TodoList,String> todoListDao = new TodoListDao();
        Dao<AuditHistory,String>  auditHistoryDao = new AuditHistoryDao();

        // Services
        TaskService taskService = new TaskServiceImpl(taskDao);
        TodoListManagerService todoListManagerService = new TodoListManagerServiceImpl(todoListDao,auditHistoryDao);

        // Create a user
        User user = new User();
        user.setUserId("user1");
        user.setUsername("rka8870");

        // Create a task
        Task task1 = new Task();
        task1.setTaskId("1");
        task1.setTag(Tag.IMPORTANT);
        task1.setStatus(TaskStatus.CREATED);
        task1.setUserId(user.getUserId());
        taskService.createTask(task1);

        System.out.println("Task 1 after creation : " + taskService.getTask("1"));

        // Update a task
        task1.setTobyDate(System.currentTimeMillis());
        taskService.updateTask(task1);

        // Get a task
        System.out.println("Task 1 after updating complete by date : " + taskService.getTask("1"));

        // Delete a task
       // taskService.deleteTask("1");

        // Create a todo list
        TodoList todoList = new TodoList();
        todoList.setTodoListId("1");
        todoList.setName("My Todo List");
        todoList.setUserId("user1");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        todoList.setTasks(tasks);
        todoListManagerService.createTodoList(todoList);

        System.out.println("ToDo List Creation : " + todoListDao.get("1").toString());

        // Add a task to the todo list
        Task task2 = new Task();
        task2.setTaskId("2");
        task2.setTag(Tag.URGENT);
        task2.setStatus(TaskStatus.CREATED);
        task2.setUserId(user.getUserId());
        todoListManagerService.addTaskToList("1", task2);

        System.out.println("ToDo List Task Addition : " + todoListDao.get("1").toString());

        // Remove a task from the todo list
        todoListManagerService.removeTaskFromList("1", "2");

        System.out.println("ToDo List Task Removal : " + todoListDao.get("1").toString());

        // Complete a task in the todo list
        todoListManagerService.completeTaskInTodoList("1", "1");

        System.out.println("ToDo List Task Completion : " + todoListDao.get("1").toString());

        System.out.println("Task 1 after marking completion in todo list : " + taskService.getTask("1"));


        // ToDo list with Audit History visible
        System.out.println(todoListDao.get("1").toString());
    }
}