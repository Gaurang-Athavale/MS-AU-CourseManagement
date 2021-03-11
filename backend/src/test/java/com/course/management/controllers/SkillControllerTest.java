package com.course.management.controllers;

import com.course.management.models.Skill;
import com.course.management.models.User;
import com.course.management.service.SkillService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SkillController.class)
class SkillControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

    @MockBean
    public SkillService skillService;

    @Before
    private Skill setSkills(){
        Skill skill = new Skill();
        skill.setSkillId(80);
        skill.setCourseId(82);
        skill.setSkillName("React");
        return skill;
    }

    @Test
    public void addSkillTest() throws Exception{
        Skill skill = setSkills();
        String jsonString = objectMapper.writeValueAsString(skill);
        Mockito.when(skillService.addSkill(skill)).thenReturn(skill);
        mockMvc.perform(post("/skill/addSkills")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonString)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk());
    }

    @Test
    public void getSkillsTest() throws Exception{
        ArrayList<Skill> skills = new ArrayList<>();
        Skill skill = setSkills();
        skills.add(skill);
        Mockito.when(skillService.getSkills(skill.getCourseId())).thenReturn(skills);
        mockMvc.perform(get("/skill/getSkills/" + skill.getCourseId())).andExpect(status().isOk());
    }
}