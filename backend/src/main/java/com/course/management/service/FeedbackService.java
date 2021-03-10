package com.course.management.service;

import com.course.management.models.Feedback;
import com.course.management.models.FeedbackCount;

import java.util.List;

public interface FeedbackService {

    public Feedback addFeedback(Feedback feedback);

    public List<Feedback> getFeedbacksByCourseId(int courseId);

    public List<FeedbackCount> getFeedbackCounts();

    public List<FeedbackCount>  getAverageRating();
}
