package com.test.hulkStore.controller;

import com.test.hulkStore.model.Users;
import com.test.hulkStore.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/")
    public ResponseEntity<String> addUsers(@RequestBody Users user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.addUsers(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(usersService.getUserById(id));
    }

}
