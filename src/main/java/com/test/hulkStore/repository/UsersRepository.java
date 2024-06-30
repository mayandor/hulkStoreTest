package com.test.hulkStore.repository;

import com.test.hulkStore.model.Users;

import java.util.UUID;

public interface UsersRepository {

    public void addUsers(Users users);

    public Users getUserById(Long id);
}
