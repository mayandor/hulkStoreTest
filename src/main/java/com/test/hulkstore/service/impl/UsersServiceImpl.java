package com.test.hulkstore.service.impl;

import com.test.hulkstore.exceptions.NotFoundException;
import com.test.hulkstore.model.Users;
import com.test.hulkstore.repository.UsersRepository;
import com.test.hulkstore.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public String addUsers(Users user) {
        usersRepository.addUsers(user);
        return user.getName();
    }

    @Override
    public Users getUserById(Long id) {
        Users user = usersRepository.getUserById(id);
        if(user == null) {
            throw new NotFoundException("User not found");
        }
        return user;
    }
}
