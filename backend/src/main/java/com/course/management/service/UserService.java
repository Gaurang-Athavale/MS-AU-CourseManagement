package com.course.management.service;

import com.course.management.models.User;

import java.util.List;

public interface UserService {
    public User addUser(User user);

    public List<User> getAllUsers();

    public User getUserByEmail(String email);

    public User getUserByUserId(int userId);

}
