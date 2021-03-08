package com.example.msau.backend.rowmapper;

import com.example.msau.backend.constants.Constants;
import com.example.msau.backend.models.Course;
import com.example.msau.backend.models.User;
import org.springframework.jdbc.core.RowMapper;

public class CourseRowMapper {

    public CourseRowMapper() {
    }

    public static final RowMapper<Course> CourseRowMapperLambda = (rs, rowNum) -> {

        Course course = new Course();
        course.setCourseId((rs.getInt((Constants.COURSE_ID))));
        course.setCourseName(rs.getString(Constants.COURSE_NAME));
        course.setCourseDescription(rs.getString(Constants.COURSE_DESCRIPTION));
        course.setPrerequisite(rs.getString((Constants.PREREQUISITE)));
        course.setCreatedOn(rs.getDate(Constants.CREATED_ON));
        course.setUserId(rs.getInt(Constants.USER_ID));
        return course;
    };


}
