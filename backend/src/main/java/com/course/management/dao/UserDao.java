package com.course.management.dao;

import com.course.management.models.User;

import java.util.List;

public interface UserDao {

    public User addUser(User user);


    List<User> getAllUsers();

    User getUserByEmail(String email);

    public User getUserByUserId(int userId);
}
