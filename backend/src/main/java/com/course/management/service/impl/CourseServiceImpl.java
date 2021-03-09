package com.course.management.service.impl;

import com.course.management.dao.impl.CourseDaoImpl;
import com.course.management.dao.impl.UserDaoImpl;
import com.course.management.service.CourseService;
import com.course.management.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDaoImpl courseDao;

    @Autowired
    private UserDaoImpl userDao;

    @Override
    public Course addCourse(Course course) {
//        List<User> users = userDao.getAllUsers();
//
//        for(int i = 0; i < users.size(); i++){
//            if(users.get(i).equals)
//        }

        return courseDao.addCourse(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }

    @Override
    public int editCourse(Course course) {
        return courseDao.editCourse(course);
    }

    @Override
    public List<Course> getCourseByUserId(int id) {
        return courseDao.getCourseByUserId(id);
    }

    @Override
    public void deleteCourse(int courseId) {
        courseDao.deleteCourse(courseId);
    }

    @Override
    public Course getCourseWithHighestId() {
        return courseDao.getCourseWithHighestId();
    }
}
