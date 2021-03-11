package com.course.management.controllers;

import com.course.management.models.Feedback;
import com.course.management.models.FeedbackCount;
import com.course.management.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("feedback")
@CrossOrigin(origins = "http://localhost:4200")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/addFeedback")
    public Feedback addFeedback(@RequestBody Feedback feedback){
        return feedbackService.addFeedback(feedback);
    }

    @GetMapping("/getFeedbacksByCourseId/{courseId}")
    public List<Feedback> getFeedbacksByCourseId(@PathVariable int courseId){
        return feedbackService.getFeedbacksByCourseId(courseId);
    }

    @GetMapping("/getFeedbackCounts")
    public List<FeedbackCount> getFeedbackCounts(){
        return feedbackService.getFeedbackCounts();
    }

    @GetMapping("/getAverageRating")
    public List<FeedbackCount> getAverageRating(){
        System.out.println();
        return feedbackService.getAverageRating();
    }
}
