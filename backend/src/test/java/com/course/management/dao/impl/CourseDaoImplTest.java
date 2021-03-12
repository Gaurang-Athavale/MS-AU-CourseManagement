package com.course.management.dao.impl;

import com.course.management.CourseManagementApplication;
import com.course.management.constants.Constants;
import com.course.management.models.Course;
import com.course.management.queries.Queries;
import com.course.management.rowmapper.CourseRowMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = CourseManagementApplication.class)
class CourseDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private CourseDaoImpl courseDao;

    @Before
    private Course setCourse(){
        Course course = new Course();

        course.setCourseId(25);
        course.setCourseName("Python");
        course.setCourseDescription("Basics of Python");
        course.setCreatedOn(new Date(System.currentTimeMillis()));
        course.setPrerequisite("Programming");
        course.setUserId(4);
        course.setLastModified(new Date(System.currentTimeMillis()));

        return course;
    }

    @Test
    public void addCourse() {
        Course course = setCourse();
        when(jdbcTemplate.update("INSERT INTO " + Constants.COURSE + "(" + Constants.COURSE_NAME + "," + Constants.COURSE_DESCRIPTION + "," + Constants.PREREQUISITE + "," + Constants.USER_ID + "," + Constants.CREATED_ON + "," + Constants.LAST_MODIFIED + ")VALUES(?,?,?,?,?,?)", course.getCourseName(), course.getCourseDescription(), course.getPrerequisite(), course.getUserId(), new Date(System.currentTimeMillis()), course.getLastModified())).thenReturn(25);

        course = courseDao.addCourse(course);

        assertEquals(course.getCourseId(), 25);
    }

    @Test
    void getAllCourses() {
        List<Course> courses = new ArrayList<>();
        when(jdbcTemplate.query("SELECT * FROM course", CourseRowMapper.CourseRowMapperLambda)).thenReturn(courses);

        List<Course> allCourses = courseDao.getAllCourses();

        assertEquals(allCourses, courses);
    }

//    @Test
//    void editCourse() {
//        Course course = setCourse();
//        when(jdbcTemplate.update(Queries.EDIT_COURSE, course.getCourseName(), course.getCourseDescription(), course.getPrerequisite(), course.getLastModified(), course.getCourseId())).thenReturn(1);
//
//        int result = courseDao.editCourse(course);
//
//        assertEquals();
//    }

    @Test
    void getCourseByUserId() {
        Course course = setCourse();
        List<Course> courses = new ArrayList<>();
        when(this.jdbcTemplate.query("select * from courses where user_id="+course.getUserId(), CourseRowMapper.CourseRowMapperLambda)).thenReturn(courses);

        List<Course> testCourse=courseDao.getCourseByUserId(course.getUserId());
        assertEquals(courses,testCourse);
    }

//    @Test
//    void deleteCourse() {
//        Course course = setCourse();
//        lenient().doNothing().when(this.jdbcTemplate.update("DELETE FROM course where course_id =?", course.getCourseId()));
//        lenient().doNothing().when()
//    }

    @Test
    void getCourseWithHighestId() {
        Course course = new Course();
        when(this.jdbcTemplate.queryForObject("SELECT * FROM course HAVING course_id = (SELECT MAX(course_id) FROM course)", CourseRowMapper.CourseRowMapperLambda)).thenReturn(course);

        Course course1 = courseDao.getCourseWithHighestId();

        assertEquals(course, course1);
    }
}