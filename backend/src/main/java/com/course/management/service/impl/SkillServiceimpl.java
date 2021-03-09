package com.course.management.service.impl;


import com.course.management.dao.SkillDao;
import com.course.management.models.Skill;
import com.course.management.service.SkillService;
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
