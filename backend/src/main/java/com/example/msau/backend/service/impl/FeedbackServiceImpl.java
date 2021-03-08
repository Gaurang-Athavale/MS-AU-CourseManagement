package com.example.msau.backend.service.impl;

import com.example.msau.backend.dao.FeedbackDao;
import com.example.msau.backend.models.Feedback;
import com.example.msau.backend.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.EditorAwareTag;

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
}
