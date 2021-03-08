package com.example.msau.backend.dao.impl;

import com.example.msau.backend.dao.CourseDao;
import com.example.msau.backend.models.Course;
import com.example.msau.backend.queries.Queries;
import com.example.msau.backend.rowmapper.CourseRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class CourseDaoImpl implements CourseDao {

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Override
    public Course addCourse(Course course) {
        jdbcTemplate.update(Queries.ADD_COURSE, course.getCourseName(), course.getCourseDescription(), course.getPrerequisite(), course.getUserId(), new Date(System.currentTimeMillis()), course.getLastModified());
        return course;
    }

    @Override
    public List<Course> getAllCourses() {
        return jdbcTemplate.query(Queries.GET_ALL_COURSES, CourseRowMapper.CourseRowMapperLambda);

    }

    @Override
    public int editCourse(Course course) {
        return jdbcTemplate.update(Queries.EDIT_COURSE, course.getCourseName(), course.getCourseDescription(), course.getPrerequisite(), new Date(System.currentTimeMillis()), course.getCourseId());
    }

    @Override
    public List<Course> getCourseByUserId(int id) {
        return jdbcTemplate.query(Queries.GET_COURSES_BY_USER_ID, CourseRowMapper.CourseRowMapperLambda, id);
    }

    @Override
    public int deleteCourse(int courseId) {
        return jdbcTemplate.update(Queries.DELETE, courseId);
    }

    @Override
    public Course getCourseWithHighestId() {
        return jdbcTemplate.queryForObject(Queries.GET_COURSE_WITH_HIGHEST_ID, CourseRowMapper.CourseRowMapperLambda);
    }



}
