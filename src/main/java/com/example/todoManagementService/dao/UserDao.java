package com.example.todoManagementService.dao;

import com.example.todoManagementService.exception.EntityAlreadyExistException;
import com.example.todoManagementService.exception.EntityNotFoundException;
import com.example.todoManagementService.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserDao implements Dao<User,String>{


    Map<String, User> map;

    public UserDao() {
        this.map = new HashMap<>();
    }

    @Override
    public void create(User data) throws EntityAlreadyExistException {
        if(map.containsKey(data.getUserId())){
            throw new EntityAlreadyExistException("Task Already Exists " + data.toString());
        }
        map.put(data.getUserId(),data);
    }

    @Override
    public void update(String s, User data) throws EntityNotFoundException {
        if(!map.containsKey(s)){
            throw new EntityNotFoundException("Task not found for update " + data.toString());
        }
        map.put(s,data);
    }

    @Override
    public User get(String s) throws EntityNotFoundException{
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
