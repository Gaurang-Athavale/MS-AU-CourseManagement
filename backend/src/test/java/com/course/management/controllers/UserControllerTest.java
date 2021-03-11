package com.course.management.controllers;

import com.course.management.CourseManagementApplication;
import com.course.management.dao.impl.UserDaoImpl;
import com.course.management.models.User;
import com.course.management.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = UserController.class)
//@SpringBootTest(classes = CourseManagementApplication.class)
class UserControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

    @MockBean
    public UserService userService;

    @Before
    private User setUsers(){
        User user = new User();
        user.setFirstName("Gaurang Athavale");
        user.setEmail("gaurang.athavale@accolitedigital.com");
        user.setUserId(3);

        return user;
    }

    @Test
    public void getAllUsersTest() throws Exception{
        ArrayList<User> usersList = new ArrayList<>();
        User user = setUsers();
        usersList.add(user);
        Mockito.when(userService.getAllUsers()).thenReturn(usersList);
        mockMvc.perform(get("/getAllUsers")).andExpect(status().isOk());
    }

    @Test
    public void addUserTest() throws Exception{
        User user = setUsers();
        String jsonString = objectMapper.writeValueAsString(user);
        Mockito.when(userService.addUser(user)).thenReturn(user);
        mockMvc.perform(post("/addUser")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonString)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk());
    }

    @Test
    public void getUserByEmailTest() throws Exception{
        User user = setUsers();
        Mockito.when(userService.getUserByEmail(user.getEmail())).thenReturn(user);
        mockMvc.perform(get("/getUserByEmail/" + user.getEmail())).andExpect(status().isOk());
    }

    @Test
    public void getUserByUserIdTest() throws Exception{
        User user = setUsers();
        Mockito.when(userService.getUserByUserId(user.getUserId())).thenReturn(user);
        mockMvc.perform(get("/getUserByUserId/" + user.getUserId())).andExpect(status().isOk());
    }


}