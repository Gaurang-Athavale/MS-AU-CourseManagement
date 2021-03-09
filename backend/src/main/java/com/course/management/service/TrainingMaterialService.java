package com.course.management.service;

import com.course.management.models.TrainingMaterial;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TrainingMaterialService {

    public TrainingMaterial addMaterial(TrainingMaterial trainingMaterial, MultipartFile file) throws IOException;

    public List<TrainingMaterial> getMaterialByCourseId(int courseId);

    public List<TrainingMaterial> getMaterialPreviousVersions(int courseId);

    public void deleteMaterial(int courseId);
}
