package com.example.msau.backend.service;

import com.example.msau.backend.models.Skill;

import java.util.List;

public interface SkillService {

    public Skill addSkill(Skill skill);

    public List<Skill> getSkills(int courseId);


}
