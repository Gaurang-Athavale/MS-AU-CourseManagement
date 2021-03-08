package com.example.msau.backend.service;

import com.example.msau.backend.models.Course;
import com.example.msau.backend.models.Skill;

import java.util.List;

public interface CourseService {
    public Course addCourse(Course course);

    public List<Course> getAllCourses();

    public int editCourse(Course course);

    public List<Course> getCourseByUserId(int id);

    public void deleteCourse(int courseId);

    public Course getCourseWithHighestId();

}

