package com.course.management.dao.impl;

import com.course.management.dao.SkillDao;
import com.course.management.models.Skill;
import com.course.management.queries.Queries;
import com.course.management.rowmapper.SkillRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SkillDaoImpl implements SkillDao {

    Logger logger= LoggerFactory.getLogger(SkillDaoImpl.class);


    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Override
    public Skill addSkill(Skill skill) {
        String [] skills = skill.getSkillName().split(";");
        for( int i = 0; i < skills.length; i++) {
            jdbcTemplate.update(Queries.ADD_SKILL, skills[i], skill.getCourseId());
        }
        logger.info("Added skill with skillId = " + skill.getSkillId());
        return skill;
    }

    @Override
    public List<Skill> getSkills(int courseId) {
        logger.info("Retrieved skills with courseId = " + courseId);
        return jdbcTemplate.query(Queries.GET_SKILLS, SkillRowMapper.SkillRowMapperLambda,courseId);
    }
}
