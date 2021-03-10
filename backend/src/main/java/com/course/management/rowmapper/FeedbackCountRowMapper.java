package com.course.management.rowmapper;

import com.course.management.constants.Constants;
//import com.course.management.models.Feedback;
import com.course.management.models.FeedbackCount;
import org.springframework.jdbc.core.RowMapper;

public class FeedbackCountRowMapper {

    public FeedbackCountRowMapper() {
    }

    public static final RowMapper<FeedbackCount> FeedbackCountRowMapperLambda = (rs, rowNum) -> {

        FeedbackCount feedbackCount = new FeedbackCount();

        feedbackCount.setCount(rs.getInt(Constants.RESULT));
        feedbackCount.setCourseName(rs.getString(Constants.COURSE_NAME));


        return feedbackCount;
    };

    public static final RowMapper<FeedbackCount> AverageRatingRowMapperLambda = (rs, rowNum) -> {

        FeedbackCount feedbackCount = new FeedbackCount();

        feedbackCount.setAvgRating(rs.getFloat(Constants.RESULT));
        feedbackCount.setCourseName(rs.getString(Constants.COURSE_NAME));

        return feedbackCount;
    };
}
