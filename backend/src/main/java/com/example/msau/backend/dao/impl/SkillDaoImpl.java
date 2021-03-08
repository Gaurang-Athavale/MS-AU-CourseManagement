package com.example.msau.backend.dao.impl;

import com.example.msau.backend.dao.SkillDao;
import com.example.msau.backend.models.Skill;
import com.example.msau.backend.queries.Queries;
import com.example.msau.backend.rowmapper.SkillRowMapper;
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
