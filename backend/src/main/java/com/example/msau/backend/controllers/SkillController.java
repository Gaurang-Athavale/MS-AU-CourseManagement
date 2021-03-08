package com.example.msau.backend.controllers;

import com.example.msau.backend.models.Skill;
import com.example.msau.backend.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("skill")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @PostMapping("/addSkills")
    @CrossOrigin(origins = "http://localhost:4200")
    public Skill addSkill(@RequestBody Skill skill){
        return skillService.addSkill(skill);
    }

    @GetMapping("getSkills/{courseId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Skill> getSkills(@PathVariable int courseId){
        return skillService.getSkills(courseId);
    }
}
