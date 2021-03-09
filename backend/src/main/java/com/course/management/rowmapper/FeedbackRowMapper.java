package com.course.management.rowmapper;

import com.course.management.constants.Constants;
import com.course.management.models.Feedback;
import org.springframework.jdbc.core.RowMapper;

public class FeedbackRowMapper {

    public FeedbackRowMapper() {
    }

    public static final RowMapper<Feedback> FeedbackRowMapperLambda = (rs, rowNum) -> {

        Feedback feedback = new Feedback();

        feedback.setFeedbackId((rs.getInt(Constants.FEEDBACK_ID)));
        feedback.setFeedbackText((rs.getString(Constants.FEEDBACK_TEXT)));
        feedback.setCourseId((rs.getInt(Constants.COURSE_ID)));
        feedback.setParticipantName((rs.getString(Constants.PARTICIPANT_NAME)));
        feedback.setCreatedOn((rs.getDate(Constants.CREATED_ON)));


        return feedback;
    };
}
