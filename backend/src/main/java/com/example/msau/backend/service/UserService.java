package com.example.msau.backend.service;

import com.example.msau.backend.models.User;

import java.util.List;

public interface UserService {
    public User addUser(User user);

    public List<User> getAllUsers();

    public User getUserByEmail(String email);
}
