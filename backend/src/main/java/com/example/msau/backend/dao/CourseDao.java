package com.example.msau.backend.dao;

import com.example.msau.backend.models.Course;

import java.util.List;

public interface CourseDao {
    public Course addCourse(Course course);

    public List<Course> getAllCourses();

    public int editCourse(Course course);

    List<Course> getCourseByUserId(int id);

    int deleteCourse(int courseId);

    public Course getCourseWithHighestId();
}
