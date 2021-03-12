package com.course.management.service.impl;

import com.course.management.CourseManagementApplication;
import com.course.management.dao.impl.CourseDaoImpl;
import com.course.management.models.Course;
import com.course.management.models.User;
import com.course.management.service.CourseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CourseManagementApplication.class)
@RunWith(SpringRunner.class)
public class CourseServiceImplTest {

    @Autowired
    private CourseServiceImpl courseService;

    @MockBean
    private CourseDaoImpl courseDao;

    Course course = new Course();

    @Before
    public void init(){
        course.setCourseId(1);
        course.setCourseName("Python");
        course.setCourseDescription("Basics of Python");
        course.setCreatedOn(new Date(System.currentTimeMillis()));
        course.setPrerequisite("Programming");
        course.setUserId(4);
        course.setLastModified(new Date(System.currentTimeMillis()));
    }

    @Test
    public void addCourse() {
        when(courseDao.addCourse(course)).thenReturn(course);
        assertThat(courseService.addCourse(course)).isEqualTo(course);
    }

    @Test
    public void getAllCourses() {
        List<Course> courses = new ArrayList<>();
        when(courseDao.getAllCourses()).thenReturn(courses);
        assertThat(courseService.getAllCourses()).isEqualTo(courses);
    }

    @Test
    public void editCourse() {
        int result = 0;
        when(courseDao.editCourse(course)).thenReturn(result);
        assertThat(courseService.editCourse(course)).isEqualTo(result);
    }

    @Test
    public void getCourseByUserId() {
        List<Course> courses = new ArrayList<>();
        when(courseDao.getCourseByUserId(course.getUserId())).thenReturn(courses);
        assertThat(courseService.getCourseByUserId(course.getUserId())).isEqualTo(courses);
    }

//    @Test
//    public void deleteCourse() {
//        doNothing().when(courseDao.deleteCourse(course.getCourseId()));
//    }

    @Test
    public void getCourseWithHighestId() {
        when(courseDao.getCourseWithHighestId()).thenReturn(course);
        assertThat(courseService.getCourseWithHighestId()).isEqualTo(course);
    }
}