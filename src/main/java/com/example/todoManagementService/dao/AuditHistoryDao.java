package com.example.todoManagementService.dao;

import com.example.todoManagementService.exception.EntityAlreadyExistException;
import com.example.todoManagementService.exception.EntityNotFoundException;
import com.example.todoManagementService.model.AuditHistory;

import java.util.HashMap;
import java.util.Map;

public class AuditHistoryDao implements Dao<AuditHistory,String>{
    Map<String, AuditHistory> map;

    public AuditHistoryDao() {
        this.map = new HashMap<>();
    }

    @Override
    public void create(AuditHistory data) throws EntityAlreadyExistException {
        if(map.containsKey(data.getAuditId())){
            throw new EntityAlreadyExistException("AuditHistory Already Exists " + data.toString());
        }
        map.put(data.getAuditId(),data);
    }

    @Override
    public void update(String s, AuditHistory data) throws EntityNotFoundException{
        if(!map.containsKey(s)){
            throw new EntityNotFoundException("AuditHistory not found for update " + data.toString());
        }
        map.put(s,data);
    }

    @Override
    public AuditHistory get(String s) throws EntityNotFoundException{
        if(!map.containsKey(s)){
            throw new EntityNotFoundException("AuditHistory not found id :" + s);
        }
        return map.get(s);
    }

    @Override
    public void delete(String s) throws EntityNotFoundException{
        if(!map.containsKey(s)){
            throw new EntityNotFoundException("AuditHistory not found for delete id : " + s);
        }
        map.remove(s);
    }
}
