package com.course.management.controllers;

import com.course.management.models.TrainingMaterial;
import com.course.management.service.TrainingMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("material")
@CrossOrigin(origins = "http://localhost:4200")
public class TrainingMaterialController {

    @Autowired
    private TrainingMaterialService trainingMaterialService;

    @PostMapping("/addMaterial")
    public TrainingMaterial addMaterial(@RequestParam String materialId, @RequestParam String courseId, @RequestParam String fileName, @RequestParam String fileType, @RequestParam MultipartFile file) throws IOException {
        TrainingMaterial trainingMaterial = new TrainingMaterial();
        trainingMaterial.setCourseId(Integer.parseInt(courseId));
        trainingMaterial.setFileName(fileName);
        trainingMaterial.setFileType(fileType);
        trainingMaterial.setMaterialId(Integer.parseInt(materialId));
        System.out.println(trainingMaterial.getCourseId());
        return trainingMaterialService.addMaterial(trainingMaterial, file);
    }

    @GetMapping("/getMaterial/{courseId}")
    public List<TrainingMaterial> getMaterialByCourseId(@PathVariable int courseId){
        return trainingMaterialService.getMaterialByCourseId(courseId);
    }

    @GetMapping("/getMaterialPreviousVersions/{courseId}/{materialId}")
    public List<TrainingMaterial> getMaterialPreviousVersions(@PathVariable("courseId") int courseId, @PathVariable("materialId") int materialId){
        return trainingMaterialService.getMaterialPreviousVersions(courseId, materialId);
    }

    @DeleteMapping("/deleteMaterial/{materialId}")
    public void deleteMaterial(@PathVariable int materialId){
        trainingMaterialService.deleteMaterial(materialId);
    }

}
