package com.example.msau.backend.rowmapper;

import com.example.msau.backend.constants.Constants;
import com.example.msau.backend.models.User;
import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper {

    public UserRowMapper() {
    }

    public static final RowMapper<User> UserRowMapperLambda = (rs, rowNum) -> {

        User user = new User();
        user.setEmail(rs.getString(Constants.EMAIL));
        user.setUserId(rs.getInt(Constants.USER_ID));

        return user;
    };

}
