package com.course.management.dao.impl;

import com.course.management.models.FeedbackCount;
import com.course.management.queries.Queries;
import com.course.management.rowmapper.FeedbackCountRowMapper;
import com.course.management.rowmapper.FeedbackRowMapper;
import com.course.management.dao.FeedbackDao;
import com.course.management.models.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class FeedbackDaoImpl implements FeedbackDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Feedback addFeedback(Feedback feedback) {
        jdbcTemplate.update(Queries.ADD_FEEDBACK, feedback.getFeedbackText(), feedback.getCourseId(), new Date(System.currentTimeMillis()), feedback.getParticipantName(), feedback.getRating());
        return feedback;
    }

    @Override
    public List<Feedback> getFeedbacksByCourseId(int courseId) {
        return jdbcTemplate.query(Queries.GET_FEEDBACK_BY_COURSE_ID, FeedbackRowMapper.FeedbackRowMapperLambda, courseId);
    }

    @Override
    public List<FeedbackCount> getFeedbackCounts() {
        return jdbcTemplate.query(Queries.GET_COUNT_OF_FEEDBACKS, FeedbackCountRowMapper.FeedbackCountRowMapperLambda);
    }

    @Override
    public List<FeedbackCount> getAverageRating() {
        return jdbcTemplate.query(Queries.GET_AVERAGE_RATING, FeedbackCountRowMapper.AverageRatingRowMapperLambda);

    }

}
