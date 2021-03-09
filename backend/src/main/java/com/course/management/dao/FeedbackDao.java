package com.course.management.dao;

import com.course.management.models.Feedback;

import java.util.List;

public interface FeedbackDao {

    public Feedback addFeedback(Feedback feedback);

    public List<Feedback> getFeedbacksByCourseId(int courseId);
}
