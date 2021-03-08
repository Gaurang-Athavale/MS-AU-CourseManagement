package com.example.msau.backend.queries;

import com.example.msau.backend.constants.Constants;

public class Queries {

    private Queries() {

    }

    public static final String ADD_USER = "INSERT INTO " + Constants.USER + "(" + Constants.FIRST_NAME + "," + Constants.EMAIL + "," + Constants.DATE_OF_JOINING + ")VALUES(?,?,?)";

    public static final String GET_ALL_USERS = "SELECT * FROM " + Constants.USER;

    public static final String ADD_COURSE = "INSERT INTO " + Constants.COURSE + "(" + Constants.COURSE_NAME + "," + Constants.COURSE_DESCRIPTION + "," + Constants.PREREQUISITE + "," + Constants.USER_ID + "," + Constants.CREATED_ON + "," + Constants.LAST_MODIFIED + ")VALUES(?,?,?,?,?,?)";

    public static final String GET_USERS_BY_EMAIL = "SELECT * FROM " + Constants.USER + " WHERE " + Constants.EMAIL + "=?";

    public static final String GET_ALL_COURSES = "SELECT * FROM " + Constants.COURSE;

    public static final String EDIT_COURSE = "UPDATE " + Constants.COURSE + " SET " + Constants.COURSE_NAME + "=?," + Constants.COURSE_DESCRIPTION + "=?," + Constants.PREREQUISITE + "=?," + Constants.LAST_MODIFIED + "=? WHERE " + Constants.COURSE_ID + "=?";

    public static final String GET_COURSES_BY_USER_ID = "SELECT * FROM " + Constants.COURSE + " WHERE " + Constants.USER_ID + "=?";

    public static final String DELETE = "DELETE FROM " + Constants.COURSE + " WHERE " + Constants.COURSE_ID + "=?";

    public static final String ADD_SKILL = "INSERT INTO " + Constants.SKILL + "(" + Constants.SKILL_NAME + "," + Constants.COURSE_ID + ")VALUES(?,?)";

    public static final String GET_COURSE_WITH_HIGHEST_ID = "SELECT * FROM " + Constants.COURSE + " HAVING " + Constants.COURSE_ID + " = (SELECT MAX(" + Constants.COURSE_ID + ") FROM " + Constants.COURSE + ")";

    public static final String ADD_FEEDBACK = "INSERT INTO " + Constants.FEEDBACK + "(" + Constants.FEEDBACK_TEXT + "," + Constants.COURSE_ID + "," + Constants.CREATED_ON + "," + Constants.PARTICIPANT_NAME + ")VALUES(?,?,?,?)";

    public static final String GET_FEEDBACK_BY_COURSE_ID = "SELECT * FROM " + Constants.FEEDBACK + " WHERE " + Constants.COURSE_ID + "=?";

    public static final String GET_SKILLS = "SELECT * FROM " + Constants.SKILL + " WHERE " + Constants.COURSE_ID + "=?";

    public static final String ADD_MATERIAL = "INSERT INTO " + Constants.TRAINING_MATERIAL + "(" + Constants.COURSE_ID + "," + Constants.FILE_TYPE + "," + Constants.FILE_NAME + "," + Constants.CREATED_ON + "," + Constants.LAST_MODIFIED + "," + Constants.FILE_DATA + "," + Constants.PARENT_ID + "," + Constants.isCurrent + ")VALUES(?,?,?,?,?,?,?,?)";

    public static final String GET_MATERIAL_BY_COURSE_ID = "SELECT * FROM " + Constants.TRAINING_MATERIAL + " WHERE " + Constants.COURSE_ID + "=? AND " + Constants.isCurrent + "=1";

    public static final String GET_COUNT_OF_MATERIAL_BY_COURSE_ID = "SELECT * FROM " + Constants.TRAINING_MATERIAL + " WHERE " + Constants.COURSE_ID +"=?";

    public static final String GET_HIGHEST_MATERIAL_WITH_COURSE_ID = "SELECT * FROM " + Constants.TRAINING_MATERIAL + " HAVING " + Constants.MATERIAL_ID + " = (SELECT MAX(" + Constants.MATERIAL_ID + ") FROM " + Constants.TRAINING_MATERIAL + " WHERE " + Constants.COURSE_ID + "=?)";

    public static final String GET_HIGHEST_MATERIAL_ID = "SELECT * FROM " + Constants.TRAINING_MATERIAL + " HAVING " + Constants.MATERIAL_ID + " = (SELECT MAX(" + Constants.MATERIAL_ID + ") FROM " + Constants.TRAINING_MATERIAL + ")";

    public static final String UPDATE_NEW_CHILD = "UPDATE " + Constants.TRAINING_MATERIAL + " SET " + Constants.PARENT_ID + "=?," + Constants.isCurrent + "=?" + " WHERE " + Constants.MATERIAL_ID + "=?";
}


