package com.test.hulkStore.service;

import com.test.hulkStore.model.Users;

public interface UsersService {
    public String addUsers(Users user);

    public Users getUserById(Long id);
}
