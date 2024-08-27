package com.example.todoManagementService.dao;

import com.example.todoManagementService.exception.EntityAlreadyExistException;
import com.example.todoManagementService.exception.EntityNotFoundException;

public interface Dao <T,ID>{

    void create(T data) throws EntityAlreadyExistException;

    void update(ID id, T data) throws EntityNotFoundException;

    T get(ID id) throws EntityNotFoundException;

    void delete(ID id) throws EntityNotFoundException;

}
