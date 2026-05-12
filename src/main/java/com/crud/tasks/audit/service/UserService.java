package com.crud.tasks.audit.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String getUser(int id) {
        return "User " + id;
    }

    public String updateUser(int id, String name) {
        if (id == 0) {
            throw new IllegalArgumentException("Invalid ID");
        }
        return "Updated user " + id + " to " + name;
    }
}
