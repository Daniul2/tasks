package com.crud.tasks.audit.controller;

import com.crud.tasks.audit.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
    @GetMapping("/{id}")
    public String get(@PathVariable int id){
        return service.getUser(id);
    }
    @PutMapping("/{id}")
    public String update(@PathVariable int id,@RequestParam String name){
        return service.updateUser(id, name);
    }
}
