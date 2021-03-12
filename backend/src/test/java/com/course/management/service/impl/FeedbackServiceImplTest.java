package com.course.management.service.impl;

import com.course.management.CourseManagementApplication;
import com.course.management.dao.FeedbackDao;
import com.course.management.dao.impl.FeedbackDaoImpl;
import com.course.management.models.Course;
import com.course.management.models.Feedback;
import com.course.management.models.FeedbackCount;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CourseManagementApplication.class)
@RunWith(SpringRunner.class)
public class FeedbackServiceImplTest {

    @Autowired
    private FeedbackServiceImpl feedbackService;

    @MockBean
    private FeedbackDaoImpl feedbackDao;

    Feedback feedback = new Feedback();

    @Before
    public void init(){
        feedback.setFeedbackId(2);
        feedback.setFeedbackText("Need to work on then content");
        feedback.setRating(1);
        feedback.setCourseId(82);
        feedback.setParticipantName("Gaurang Athavale");
        feedback.setCreatedOn(new Date(System.currentTimeMillis()));
    }

    @Test
    public void addFeedback() {
        when(feedbackDao.addFeedback(feedback)).thenReturn(feedback);
        assertThat(feedbackService.addFeedback(feedback)).isEqualTo(feedback);
    }

    @Test
    public void getFeedbacksByCourseId() {
        List<Feedback> feedbacks = new ArrayList<>();
        when(feedbackDao.getFeedbacksByCourseId(feedback.getCourseId())).thenReturn(feedbacks);
        assertThat(feedbackService.getFeedbacksByCourseId(feedback.getCourseId())).isEqualTo(feedbacks);
    }

    @Test
    public void getFeedbackCounts() {
        List<FeedbackCount> feedbackCounts = new ArrayList<>();
        when(feedbackDao.getFeedbackCounts()).thenReturn(feedbackCounts);
        assertThat(feedbackService.getFeedbackCounts()).isEqualTo(feedbackCounts);
    }

    @Test
    public void getAverageRating() {
        List<FeedbackCount> feedbackCounts = new ArrayList<>();
        when(feedbackDao.getAverageRating()).thenReturn(feedbackCounts);
        assertThat(feedbackService.getAverageRating()).isEqualTo(feedbackCounts);
    }
}