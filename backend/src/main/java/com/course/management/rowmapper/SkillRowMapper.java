package com.course.management.rowmapper;

import com.course.management.constants.Constants;
import com.course.management.models.Skill;
import org.springframework.jdbc.core.RowMapper;

public class SkillRowMapper {

    public SkillRowMapper() {

    }

    public static final RowMapper<Skill> SkillRowMapperLambda = (rs, rowNum) -> {
        Skill skill = new Skill();

        skill.setSkillId(rs.getInt(Constants.SKILL_ID));
        skill.setSkillName(rs.getString(Constants.SKILL_NAME));
        skill.setCourseId(rs.getInt(Constants.COURSE_ID));


        return skill;
    };
}
