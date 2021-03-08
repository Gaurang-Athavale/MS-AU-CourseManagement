package com.example.msau.backend.service;

import com.example.msau.backend.models.TrainingMaterial;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TrainingMaterialService {

    public TrainingMaterial addMaterial(TrainingMaterial trainingMaterial, MultipartFile file) throws IOException;

    public List<TrainingMaterial> getMaterialByCourseId(int courseId);
}
