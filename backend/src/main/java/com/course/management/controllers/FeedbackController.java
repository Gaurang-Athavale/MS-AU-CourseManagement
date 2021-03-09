package com.course.management.controllers;

import com.course.management.models.Feedback;
import com.course.management.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/addFeedback")
    @CrossOrigin(origins = "http://localhost:4200")
    public Feedback addFeedback(@RequestBody Feedback feedback){
        return feedbackService.addFeedback(feedback);
    }

    @GetMapping("/getFeedbacksByCourseId/{courseId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Feedback> getFeedbacksByCourseId(@PathVariable int courseId){
        return feedbackService.getFeedbacksByCourseId(courseId);
    }

}
