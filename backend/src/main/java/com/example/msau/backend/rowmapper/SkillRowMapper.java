package com.example.msau.backend.rowmapper;

import com.example.msau.backend.constants.Constants;
import com.example.msau.backend.models.Course;
import com.example.msau.backend.models.Skill;
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
