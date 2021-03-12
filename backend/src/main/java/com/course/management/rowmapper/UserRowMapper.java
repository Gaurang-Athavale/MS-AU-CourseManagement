package com.course.management.rowmapper;

import com.course.management.constants.Constants;
import com.course.management.models.User;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper {

    public UserRowMapper() {
    }

    public static final RowMapper<User> UserRowMapperLambda = (rs, rowNum) -> {

        User user = new User();
        user.setEmail(rs.getString(Constants.EMAIL));
        user.setUserId(rs.getInt(Constants.USER_ID));
        user.setFirstName(rs.getString(Constants.FIRST_NAME));
        user.setDateOfJoining(rs.getDate(Constants.DATE_OF_JOINING));

        return user;
    };

}
