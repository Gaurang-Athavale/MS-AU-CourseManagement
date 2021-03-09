package com.course.management.dao.impl;

import com.course.management.models.User;
import com.course.management.dao.UserDao;
import com.course.management.queries.Queries;
import com.course.management.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    public JdbcTemplate jdbcTemplate;

    public User addUser(User user){
        int flag = 0;
        List<User> users = getAllUsers();
        for(int i = 0; i<users.size(); i++){
            if(user.getEmail().equals(users.get(i).getEmail())){
                flag = 1;
                System.out.println(flag);
                break;
            }
        }
        if(flag == 0) {
            jdbcTemplate.update(Queries.ADD_USER, user.getFirstName(), user.getEmail(), user.getDateOfJoining());
            return user;
        }
        else{
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(Queries.GET_ALL_USERS, UserRowMapper.UserRowMapperLambda);
    }

    @Override
    public User getUserByEmail(String email) {
        System.out.println(email);
        return jdbcTemplate.queryForObject(Queries.GET_USERS_BY_EMAIL, UserRowMapper.UserRowMapperLambda, email);
    }
}
