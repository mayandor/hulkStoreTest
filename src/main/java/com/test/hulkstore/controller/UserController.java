package com.test.hulkstore.controller;

import com.test.hulkstore.model.Users;
import com.test.hulkstore.service.UsersService;
import com.test.hulkstore.utils.Utils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Utils.PATH_USERS)
public class UserController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/")
    public ResponseEntity<String> addUsers(@RequestBody @Valid Users user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.addUsers(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(usersService.getUserById(id));
    }

}
