package com.course.management.dao.impl;

import com.course.management.CourseManagementApplication;
import com.course.management.constants.Constants;
import com.course.management.models.Course;
import com.course.management.models.Feedback;
import com.course.management.models.User;
import com.course.management.queries.Queries;
import com.course.management.rowmapper.CourseRowMapper;
import com.course.management.rowmapper.FeedbackRowMapper;
import com.course.management.rowmapper.UserRowMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CourseManagementApplication.class)
class UserDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private UserDaoImpl userDao;

    @Before
    private User setUsers(){
        User user = new User();
        user.setFirstName("Gaurang Athavale");
        user.setEmail("gaurang.athavale@accolitedigital.com");
        user.setUserId(3);

        return user;
    }

    @Test
    void addUser() {
        User user = setUsers();
        when(jdbcTemplate.update(Queries.ADD_USER, user.getFirstName(), user.getEmail(), user.getDateOfJoining())).thenReturn(3);

        user = userDao.addUser(user);

        assertEquals(user.getUserId(), 3);
    }

    @Test
    void getAllUsers() {
        List<User> users = new ArrayList<>();
        when(jdbcTemplate.query("SELECT * FROM user", UserRowMapper.UserRowMapperLambda)).thenReturn(users);

        List<User> allUsers = userDao.getAllUsers();

        assertEquals(allUsers, users);
    }

//    @Test
//    void getUserByEmail() {
//        User user = setUsers();
//        System.out.println(user.getUserId());
//
//        User users = new User();
//        when(this.jdbcTemplate.queryForObject("SELECT * FROM user WHERE email =?", UserRowMapper.UserRowMapperLambda, user.getEmail())).thenReturn(users);
//
//        User testUser = userDao.getUserByEmail(user.getEmail());
//        assertEquals(users,testUser);
//    }
////
//    @Test
//    void getUserByUserId() {
//        User user = setUsers();
//        User users = new User();
//        when(this.jdbcTemplate.queryForObject("SELECT * FROM user WHERE user_id = " + user.getUserId(), UserRowMapper.UserRowMapperLambda)).thenReturn(user);
//
//        User testUser = userDao.getUserByUserId(3);
//        assertEquals(user,testUser);
//    }
}