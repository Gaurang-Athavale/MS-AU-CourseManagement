package com.course.management.dao.impl;

import com.course.management.dao.SkillDao;
import com.course.management.models.Skill;
import com.course.management.queries.Queries;
import com.course.management.rowmapper.SkillRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SkillDaoImpl implements SkillDao {

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Override
    public Skill addSkill(Skill skill) {
        String [] skills = skill.getSkillName().split(";");
        for( int i = 0; i < skills.length; i++) {
            jdbcTemplate.update(Queries.ADD_SKILL, skills[i], skill.getCourseId());
        }
        return skill;
    }

    @Override
    public List<Skill> getSkills(int courseId) {
        return jdbcTemplate.query(Queries.GET_SKILLS, SkillRowMapper.SkillRowMapperLambda,courseId);
    }
}
