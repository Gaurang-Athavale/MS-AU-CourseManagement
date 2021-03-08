package com.example.msau.backend.service.impl;


import com.example.msau.backend.dao.SkillDao;
import com.example.msau.backend.models.Skill;
import com.example.msau.backend.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceimpl implements SkillService {

    @Autowired
    private SkillDao skillDao;

    @Override
    public Skill addSkill(Skill skill) {
        return skillDao.addSkill(skill);
    }

    @Override
    public List<Skill> getSkills(int courseId) {
        return skillDao.getSkills(courseId);
    }
}
