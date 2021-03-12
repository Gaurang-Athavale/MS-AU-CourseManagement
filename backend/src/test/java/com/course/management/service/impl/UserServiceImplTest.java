package com.course.management.service.impl;

import com.course.management.CourseManagementApplication;
import com.course.management.dao.impl.UserDaoImpl;
import com.course.management.models.User;
import org.junit.Before;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CourseManagementApplication.class)
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private UserDaoImpl userDao;

    User user = new User();

    @Before
    public void init(){
        user.setFirstName("Gaurang Athavale");
        user.setEmail("gaurang.athavale@accolitedigital.com");
        user.setUserId(3);
    }

    @Test
    public void addUser() throws Exception{
        when(userDao.addUser(user)).thenReturn(user);
        assertThat(userService.addUser(user)).isEqualTo(user);
    }

    @Test
    public void getAllUsers() {
        List<User> users = new ArrayList<>();
        when(userDao.getAllUsers()).thenReturn(users);
        assertThat(userService.getAllUsers()).isEqualTo(users);
    }

    @Test
    public void getUserByEmail() {
        when(userDao.getUserByUserId(user.getUserId())).thenReturn(user);
        assertThat(userService.getUserByUserId(user.getUserId())).isEqualTo(user);
    }

    @Test
    public void getUserByUserId() {
        when(userDao.getUserByEmail(user.getEmail())).thenReturn(user);
        assertThat(userService.getUserByEmail(user.getEmail())).isEqualTo(user);
    }
}