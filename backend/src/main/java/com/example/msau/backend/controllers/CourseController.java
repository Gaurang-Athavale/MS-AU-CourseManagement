package com.example.msau.backend.controllers;

import com.example.msau.backend.models.Course;
import com.example.msau.backend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("course")
public class CourseController {

    @Autowired
    private CourseService courseService;

//    @GetMapping("/getAllCourses")
//    public Course getAllCourses(){
//        return courseService.getAllCourses();
//    }

    @PostMapping("/addCourse")
    @CrossOrigin(origins = "http://localhost:4200")
    public Course addCourse(@RequestBody Course course){
        return courseService.addCourse(course);
    }

    @GetMapping("/getAllCourses")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping("/getCourseByUserId/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Course> getCourseByUserId(@PathVariable int id){
        return courseService.getCourseByUserId(id);
    }

    @PutMapping("/editCourses")
    @CrossOrigin(origins = "http://localhost:4200")
    public int editCourse(@RequestBody Course course){
        return courseService.editCourse(course);
    }

    @DeleteMapping("/deleteCourse/{courseId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void deleteCourse(@PathVariable int courseId){
        courseService.deleteCourse(courseId);
    }

    @GetMapping("/getCourseWithHighestId")
    @CrossOrigin(origins = "http://localhost:4200")
    public Course getCourseWithHighestId(){
        return courseService.getCourseWithHighestId();
    }
}
