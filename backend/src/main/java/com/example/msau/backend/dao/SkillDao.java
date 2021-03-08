package com.example.msau.backend.dao;


import com.example.msau.backend.models.Skill;

import java.util.List;

public interface SkillDao {

    public Skill addSkill(Skill skill);

    public List<Skill> getSkills(int courseId);


}
