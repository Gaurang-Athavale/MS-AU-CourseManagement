package com.course.management.controllers;

import com.course.management.models.Feedback;
import com.course.management.models.FeedbackCount;
import com.course.management.models.Skill;
import com.course.management.service.FeedbackService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FeedbackController.class)
class FeedbackControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

    @MockBean
    public FeedbackService feedbackService;

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
    void addFeedback() throws Exception{
        Feedback feedback = setFeedback();
        String jsonString = objectMapper.writeValueAsString(feedback);
        Mockito.when(feedbackService.addFeedback(feedback)).thenReturn(feedback);
        mockMvc.perform(post("/feedback/addFeedback")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonString)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk());
    }

    @Test
    void getFeedbacksByCourseId() throws Exception{
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        Feedback feedback = setFeedback();
        feedbacks.add(feedback);
        Mockito.when(feedbackService.getFeedbacksByCourseId(feedback.getCourseId())).thenReturn(feedbacks);
        mockMvc.perform(get("/feedback/getFeedbacksByCourseId/" + feedback.getCourseId())).andExpect(status().isOk());
    }

    @Test
    void getFeedbackCounts() throws Exception{
        ArrayList<FeedbackCount> feedbackCounts = new ArrayList<>();
        FeedbackCount feedbackCount = setFeedbackCount();
        feedbackCounts.add(feedbackCount);
        Mockito.when(feedbackService.getFeedbackCounts()).thenReturn(feedbackCounts);
        mockMvc.perform(get("/feedback/getFeedbackCounts/")).andExpect(status().isOk());
    }

    @Test
    void getAverageRating() throws Exception{
        ArrayList<FeedbackCount> feedbackCounts = new ArrayList<>();
        FeedbackCount feedbackCount = setFeedbackCount();
        feedbackCounts.add(feedbackCount);
        Mockito.when(feedbackService.getAverageRating()).thenReturn(feedbackCounts);
        mockMvc.perform(get("/feedback/getAverageRating/")).andExpect(status().isOk());
    }
}