package com.course.management.dao.impl;

import com.course.management.dao.CourseDao;
import com.course.management.queries.Queries;
import com.course.management.models.Course;
import com.course.management.rowmapper.CourseRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

@Repository
public class CourseDaoImpl implements CourseDao {

    Logger logger= LoggerFactory.getLogger(CourseDaoImpl.class);

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Override
    public Course addCourse(Course course) {
        jdbcTemplate.update(Queries.ADD_COURSE, course.getCourseName(), course.getCourseDescription(), course.getPrerequisite(), course.getUserId(), new Date(System.currentTimeMillis()), course.getLastModified());
        logger.info("Added Course with courseid = " + course.getCourseId());
        return course;
    }

    @Override
    public List<Course> getAllCourses() {
        logger.info("Retrieved All Courses");
        return jdbcTemplate.query(Queries.GET_ALL_COURSES, CourseRowMapper.CourseRowMapperLambda);
    }

    @Override
    public int editCourse(Course course) {
        logger.info("Edited course with courseId = " + course.getCourseId());
        return jdbcTemplate.update(Queries.EDIT_COURSE, course.getCourseName(), course.getCourseDescription(), course.getPrerequisite(), new Date(System.currentTimeMillis()), course.getCourseId());
    }

    @Override
    public List<Course> getCourseByUserId(int id) {
        logger.info("Retrieved courses uploaded by userId = " + id);
        return jdbcTemplate.query(Queries.GET_COURSES_BY_USER_ID, CourseRowMapper.CourseRowMapperLambda, id);
    }

    @Override
    public int deleteCourse(int courseId) {
        jdbcTemplate.update(Queries.DELETE_SKILLS, courseId);
        jdbcTemplate.update(Queries.DELETE_FEEDBACKS, courseId);
        jdbcTemplate.update(Queries.DELETE_TRAINING_MATERIAL, courseId);
        logger.info("Deleted Course with courseId = " + courseId);
        return jdbcTemplate.update(Queries.DELETE_COURSE, courseId);
    }

    @Override
    public Course getCourseWithHighestId() {
        logger.info("Retrieved course with highest courseId");
        return jdbcTemplate.queryForObject(Queries.GET_COURSE_WITH_HIGHEST_ID, CourseRowMapper.CourseRowMapperLambda);
    }

}
