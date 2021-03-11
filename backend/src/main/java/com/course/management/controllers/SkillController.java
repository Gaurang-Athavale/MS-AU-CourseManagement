package com.course.management.controllers;

import com.course.management.models.Skill;
import com.course.management.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("skill")
@CrossOrigin(origins = "http://localhost:4200")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @PostMapping("/addSkills")
    public Skill addSkill(@RequestBody Skill skill){
        return skillService.addSkill(skill);
    }

    @GetMapping("getSkills/{courseId}")
    public List<Skill> getSkills(@PathVariable int courseId){
        return skillService.getSkills(courseId);
    }
}
