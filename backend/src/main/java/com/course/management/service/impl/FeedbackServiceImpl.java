package com.course.management.service.impl;

import com.course.management.models.FeedbackCount;
import com.course.management.service.FeedbackService;
import com.course.management.dao.FeedbackDao;
import com.course.management.models.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackDao feedbackDao;


    @Override
    public Feedback addFeedback(Feedback feedback) {
        return feedbackDao.addFeedback(feedback);
    }

    @Override
    public List<Feedback> getFeedbacksByCourseId(int courseId) {
        return feedbackDao.getFeedbacksByCourseId(courseId);
    }

    @Override
    public List<FeedbackCount> getFeedbackCounts() {
        return feedbackDao.getFeedbackCounts();
    }

    @Override
    public List<FeedbackCount> getAverageRating() {
        return feedbackDao.getAverageRating();
    }
}
