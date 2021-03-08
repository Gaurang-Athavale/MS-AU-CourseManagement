package com.example.msau.backend.service;

import com.example.msau.backend.models.Feedback;

import java.util.List;

public interface FeedbackService {

    public Feedback addFeedback(Feedback feedback);

    public List<Feedback> getFeedbacksByCourseId(int courseId);

}
