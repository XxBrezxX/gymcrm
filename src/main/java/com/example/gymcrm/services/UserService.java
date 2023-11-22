package com.example.gymcrm.services;

import java.util.List;

import com.example.gymcrm.model.User;

public interface UserService {
    User createUser(User user);

    User updateUser(User user);

    void deleteUser(Long id);

    User getUserById(Long id);

    List<User> getAllUsers();

    void deleteAll();

}
