package com.example.msau.backend.dao;

import com.example.msau.backend.models.Course;
import com.example.msau.backend.models.User;

import java.util.List;

public interface UserDao {

    public User addUser(User user);


    List<User> getAllUsers();

    User getUserByEmail(String email);
}
