package com.course.management.dao.impl;

import com.course.management.CourseManagementApplication;
import com.course.management.models.Feedback;
import com.course.management.models.FeedbackCount;
import com.course.management.rowmapper.FeedbackCountRowMapper;
import com.course.management.rowmapper.FeedbackRowMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CourseManagementApplication.class)
class FeedbackDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private FeedbackDaoImpl feedbackDao;

    @Before
    private Feedback setFeedback(){
        Feedback feedback = new Feedback();

        feedback.setFeedbackId(2);
        feedback.setFeedbackText("Need to work on then content");
        feedback.setRating(1);
        feedback.setCourseId(82);
        feedback.setParticipantName("Gaurang Athavale");
        feedback.setCreatedOn(new Date(System.currentTimeMillis()));

        return feedback;
    }

    @Before
    private FeedbackCount setFeedbackCount(){
        FeedbackCount feedbackCount = new FeedbackCount();

        feedbackCount.setCount(10);
        feedbackCount.setAvgRating(4);
        feedbackCount.setCourseName("Python");

        return feedbackCount;
    }

    @Test
    void addFeedback() {
    }

    @Test
    void getFeedbacksByCourseId() {
        Feedback feedback = setFeedback();
        List<Feedback> feedbacks = new ArrayList<>();
        when(this.jdbcTemplate.query("SELECT * FROM feedback WHERE course_id = " + feedback.getCourseId(), FeedbackRowMapper.FeedbackRowMapperLambda)).thenReturn(feedbacks);

        List<Feedback> testFeedback=feedbackDao.getFeedbacksByCourseId(feedback.getCourseId());
        assertEquals(feedbacks,testFeedback);
    }

    @Test
    void getFeedbackCounts() {
        FeedbackCount feedbackCount = setFeedbackCount();
        List<FeedbackCount> feedbackCounts = new ArrayList<>();
        when(this.jdbcTemplate.query("SELECT course_id, count(*) FROM feedback JOIN course where feedback.course_id = course.course_id GROUP BY course_name ORDER BY count(*) DESC", FeedbackCountRowMapper.FeedbackCountRowMapperLambda)).thenReturn(feedbackCounts);

        List<FeedbackCount> testFeedbackCount = feedbackDao.getFeedbackCounts();
        assertEquals(feedbackCounts, testFeedbackCount);
    }

    @Test
    void getAverageRating() {
        FeedbackCount rating = setFeedbackCount();
        List<FeedbackCount> ratings = new ArrayList<>();
        when(this.jdbcTemplate.query("SELECT course_id, AVG(rating) FROM feedback JOIN course where feedback.course_id = course.course_id GROUP BY course_name ORDER BY AVG(rating) DESC", FeedbackCountRowMapper.FeedbackCountRowMapperLambda)).thenReturn(ratings);

        List<FeedbackCount> testRatings = feedbackDao.getAverageRating();
        assertEquals(ratings, testRatings);
    }
}