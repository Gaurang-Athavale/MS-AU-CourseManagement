package com.example.msau.backend.controllers;

import com.example.msau.backend.models.TrainingMaterial;
import com.example.msau.backend.service.TrainingMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("material")
public class TrainingMaterialController {

    @Autowired
    private TrainingMaterialService trainingMaterialService;

    @PostMapping("/addMaterial")
    @CrossOrigin(origins = "http://localhost:4200")
    public TrainingMaterial addMaterial(@RequestParam String courseId, @RequestParam String fileName, @RequestParam String fileType, @RequestParam MultipartFile file) throws IOException {
        TrainingMaterial trainingMaterial = new TrainingMaterial();
        trainingMaterial.setCourseId(Integer.parseInt(courseId));
        trainingMaterial.setFileName(fileName);
        trainingMaterial.setFileType(fileType);
        System.out.println(trainingMaterial.getCourseId());
        return trainingMaterialService.addMaterial(trainingMaterial, file);
    }

    @GetMapping("/getMaterial/{courseId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<TrainingMaterial> getMaterialByCourseId(@PathVariable int courseId){
        return trainingMaterialService.getMaterialByCourseId(courseId);
    }

}
