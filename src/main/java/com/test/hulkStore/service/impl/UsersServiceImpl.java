package com.test.hulkStore.service.impl;

import com.test.hulkStore.exceptions.NotFoundException;
import com.test.hulkStore.model.Users;
import com.test.hulkStore.repository.UsersRepository;
import com.test.hulkStore.service.UsersService;
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
