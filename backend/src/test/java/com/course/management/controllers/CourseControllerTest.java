package com.course.management.controllers;

import com.course.management.models.Course;
import com.course.management.models.Feedback;
import com.course.management.service.CourseService;
import com.course.management.service.SkillService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CourseController.class)
class CourseControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

    @MockBean
    public CourseService courseService;

    @Before
    private Course setCourse(){
        Course course = new Course();

        course.setCourseId(26);
        course.setCourseName("Python");
        course.setCourseDescription("Basics of Python");
        course.setCreatedOn(new Date(System.currentTimeMillis()));
        course.setPrerequisite("Programming");
        course.setUserId(4);
        course.setLastModified(new Date(System.currentTimeMillis()));

        return course;
    }

    @Test
    void addCourse() throws Exception{
        Course course = setCourse();
        String jsonString = objectMapper.writeValueAsString(course);
        Mockito.when(courseService.addCourse(course)).thenReturn(course);
        mockMvc.perform(post("/course/addCourse")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonString)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk());
    }

    @Test
    void getAllCourses() throws Exception{
        ArrayList<Course> courses = new ArrayList<>();
        Course course = setCourse();
        courses.add(course);
        Mockito.when(courseService.getAllCourses()).thenReturn(courses);
        mockMvc.perform(get("/course/getAllCourses/")).andExpect(status().isOk());
    }

    @Test
    void getCourseByUserId() throws Exception{
        ArrayList<Course> courses = new ArrayList<>();
        Course course = setCourse();
        courses.add(course);
        Mockito.when(courseService.getCourseByUserId(course.getUserId())).thenReturn(courses);
        mockMvc.perform(get("/course/getCourseByUserId/" + course.getUserId())).andExpect(status().isOk());
    }

    @Test
    void editCourse() throws Exception{
        Course course = setCourse();
        String jsonString = objectMapper.writeValueAsString(course);
        Mockito.when(courseService.editCourse(course)).thenReturn(1);
        mockMvc.perform(put("/course/editCourses")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonString)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk());
    }

    @Test
    void deleteCourse() throws Exception{
        Course course = setCourse();
        Mockito.doNothing().when(courseService).deleteCourse(course.getCourseId());
        mockMvc.perform(delete("/course/deleteCourse/"+course.getCourseId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(String.valueOf(course.getCourseId()))
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk());
    }

    @Test
    void getCourseWithHighestId() throws Exception{
        Course course = setCourse();
        Mockito.when(courseService.getCourseWithHighestId()).thenReturn(course);
        mockMvc.perform(get("/course/getCourseWithHighestId/")).andExpect(status().isOk());
    }
}