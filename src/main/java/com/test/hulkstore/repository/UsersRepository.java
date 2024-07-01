package com.test.hulkstore.repository;

import com.test.hulkstore.model.Users;

public interface UsersRepository {

    public void addUsers(Users users);

    public Users getUserById(Long id);
}
