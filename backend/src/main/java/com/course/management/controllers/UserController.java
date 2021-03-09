package com.course.management.controllers;

import com.course.management.models.User;
import com.course.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    @CrossOrigin(origins = "http://localhost:4200")
    public User addUser(@RequestBody User user){
        System.out.println(user.getEmail());
        return userService.addUser(user);
    }

    @GetMapping("/getAllUsers")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/getUserByEmail/{email}")
    @CrossOrigin(origins = "http://localhost:4200")
    public User getUserByEmail(@PathVariable String email){
        User user = userService.getUserByEmail(email);
        System.out.println(user.getUserId());
//        return user;
        System.out.println(email);
        return user;
    }
}
