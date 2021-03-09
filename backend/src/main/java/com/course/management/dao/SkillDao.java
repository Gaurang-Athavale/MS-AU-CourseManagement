package com.course.management.dao;


import com.course.management.models.Skill;

import java.util.List;

public interface SkillDao {

    public Skill addSkill(Skill skill);

    public List<Skill> getSkills(int courseId);


}
