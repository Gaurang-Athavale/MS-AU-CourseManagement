package com.course.management.dao.impl;

import com.course.management.CourseManagementApplication;
import com.course.management.models.Course;
import com.course.management.models.Skill;
import com.course.management.rowmapper.SkillRowMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CourseManagementApplication.class)
class SkillDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private SkillDaoImpl skillDao;

    @Before
    private Skill setSkills(){
        Skill skill = new Skill();
        skill.setSkillId(80);
        skill.setCourseId(82);
        skill.setSkillName("React");
        return skill;
    }

    @Test
    void addSkill() {
    }

    @Test
    void getSkills() {
        Skill skill = setSkills();
        List<Skill> skills = new ArrayList<>();
        when(this.jdbcTemplate.query("SELECT * FROM skills WHERE course_id = " + skill.getCourseId(), SkillRowMapper.SkillRowMapperLambda)).thenReturn(skills);

        List<Skill> testSkill=skillDao.getSkills(skill.getCourseId());
        assertEquals(skills,testSkill);
    }
}