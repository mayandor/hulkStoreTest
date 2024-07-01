package com.test.hulkstore.service;

import com.test.hulkstore.model.Users;

public interface UsersService {
    public String addUsers(Users user);

    public Users getUserById(Long id);
}
