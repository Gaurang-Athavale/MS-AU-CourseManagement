package com.course.management.service;

import com.course.management.models.Skill;

import java.util.List;

public interface SkillService {

    public Skill addSkill(Skill skill);

    public List<Skill> getSkills(int courseId);


}
