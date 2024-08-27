package com.example.todoManagementService.dao;

import com.example.todoManagementService.exception.EntityAlreadyExistException;
import com.example.todoManagementService.exception.EntityNotFoundException;
import com.example.todoManagementService.model.Task;

import java.util.HashMap;
import java.util.Map;

public class TaskDao implements Dao<Task,String>{

    Map<String, Task> map;

    public TaskDao() {
        this.map = new HashMap<>();
    }

    @Override
    public void create(Task data) throws EntityAlreadyExistException {
        if(map.containsKey(data.getTaskId())){
            throw new EntityAlreadyExistException("Task Already Exists " + data.toString());
        }
        map.put(data.getTaskId(),data);
    }

    @Override
    public void update(String s, Task data) throws EntityNotFoundException{
        if(!map.containsKey(s)){
            throw new EntityNotFoundException("Task not found for update " + data.toString());
        }
        map.put(s,data);
    }

    @Override
    public Task get(String s) throws EntityNotFoundException{
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
