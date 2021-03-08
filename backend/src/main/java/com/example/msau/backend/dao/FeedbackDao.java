package com.example.msau.backend.dao;

import com.example.msau.backend.models.Feedback;

import java.util.List;

public interface FeedbackDao {

    public Feedback addFeedback(Feedback feedback);

    public List<Feedback> getFeedbacksByCourseId(int courseId);
}
