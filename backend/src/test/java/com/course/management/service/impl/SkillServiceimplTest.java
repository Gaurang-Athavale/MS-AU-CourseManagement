package com.course.management.service.impl;

import com.course.management.CourseManagementApplication;
import com.course.management.dao.impl.SkillDaoImpl;
import com.course.management.models.Skill;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CourseManagementApplication.class)
@RunWith(SpringRunner.class)
public class SkillServiceimplTest {

    @Autowired
    private SkillServiceimpl skillServiceimpl;

    @MockBean
    private SkillDaoImpl skillDao;

    Skill skill = new Skill();

    @Before
    public void init(){
        skill.setSkillId(1);
        skill.setSkillName("Python");
        skill.setCourseId(2);
    }

    @Test
    public void addSkill() {
        when(skillDao.addSkill(skill)).thenReturn(skill);
        assertThat(skillServiceimpl.addSkill(skill)).isEqualTo(skill);
    }

    @Test
    public void getSkills() {
        List<Skill> skills = new ArrayList<>();
        when(skillDao.getSkills(skill.getCourseId())).thenReturn(skills);
        assertThat(skillServiceimpl.getSkills(skill.getCourseId())).isEqualTo(skills);
    }
}