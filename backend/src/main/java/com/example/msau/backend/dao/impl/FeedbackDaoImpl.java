package com.example.msau.backend.dao.impl;

import com.example.msau.backend.dao.FeedbackDao;
import com.example.msau.backend.models.Feedback;
import com.example.msau.backend.queries.Queries;
import com.example.msau.backend.rowmapper.FeedbackRowMapper;
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
        jdbcTemplate.update(Queries.ADD_FEEDBACK, feedback.getFeedbackText(), feedback.getCourseId(), new Date(System.currentTimeMillis()), feedback.getParticipantName());
        return feedback;
    }

    @Override
    public List<Feedback> getFeedbacksByCourseId(int courseId) {
        return jdbcTemplate.query(Queries.GET_FEEDBACK_BY_COURSE_ID, FeedbackRowMapper.FeedbackRowMapperLambda, courseId);
    }

}
