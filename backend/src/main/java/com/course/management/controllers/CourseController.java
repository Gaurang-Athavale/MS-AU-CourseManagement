package com.course.management.controllers;

import com.course.management.service.CourseService;
import com.course.management.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("course")
@CrossOrigin(origins = "http://localhost:4200")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/addCourse")
    public Course addCourse(@RequestBody Course course){
        return courseService.addCourse(course);
    }

    @GetMapping("/getAllCourses")
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping("/getCourseByUserId/{id}")
    public List<Course> getCourseByUserId(@PathVariable int id){
        return courseService.getCourseByUserId(id);
    }

    @PutMapping("/editCourses")
    public int editCourse(@RequestBody Course course){
        return courseService.editCourse(course);
    }

    @DeleteMapping("/deleteCourse/{courseId}")
    public void deleteCourse(@PathVariable int courseId){
        courseService.deleteCourse(courseId);
    }

    @GetMapping("/getCourseWithHighestId")
    public Course getCourseWithHighestId(){
        return courseService.getCourseWithHighestId();
    }
}
