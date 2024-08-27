package com.example.todoManagementService.dao;

import com.example.todoManagementService.exception.EntityAlreadyExistException;
import com.example.todoManagementService.exception.EntityNotFoundException;
import com.example.todoManagementService.model.TodoList;

import java.util.HashMap;
import java.util.Map;

public class TodoListDao implements Dao<TodoList, String>{


    Map<String, TodoList> map;

    public TodoListDao() {
        this.map = new HashMap<>();
    }

    @Override
    public void create(TodoList data) throws EntityAlreadyExistException {
        if(map.containsKey(data.getTodoListId())){
            throw new EntityAlreadyExistException("Task Already Exists " + data.toString());
        }
        map.put(data.getTodoListId(),data);
    }

    @Override
    public void update(String s, TodoList data) throws EntityNotFoundException{
        if(!map.containsKey(s)){
            throw new EntityNotFoundException("Task not found for update " + data.toString());
        }
        map.put(s,data);
    }

    @Override
    public TodoList get(String s) throws EntityNotFoundException{
        if(!map.containsKey(s)){
            throw new EntityNotFoundException("Task not found id :" + s);
        }
        return map.get(s);
    }

    @Override
    public void delete(String s) throws EntityNotFoundException{
        if(!map.containsKey(s)){
            throw new EntityNotFoundException("Task not found for delete id : " + s);
        }
        map.remove(s);
    }
}
