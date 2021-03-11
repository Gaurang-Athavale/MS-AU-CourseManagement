package com.course.management.service.impl;

import com.course.management.dao.TrainingMaterialDao;
import com.course.management.models.TrainingMaterial;
import com.course.management.service.TrainingMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class TrainingMaterialServiceImpl implements TrainingMaterialService {

    @Autowired
    private TrainingMaterialDao trainingMaterialDao;

    @Override
    public TrainingMaterial addMaterial(TrainingMaterial trainingMaterial, MultipartFile file) throws IOException {
        return trainingMaterialDao.addMaterial(trainingMaterial, file);
    }

    @Override
    public List<TrainingMaterial> getMaterialByCourseId(int courseId) {
        return trainingMaterialDao.getMaterialByCourseId(courseId);
    }

    @Override
    public List<TrainingMaterial> getMaterialPreviousVersions(int courseId, int materialId) {
        return trainingMaterialDao.getMaterialPreviousVersions(courseId, materialId);
    }

    @Override
    public void deleteMaterial(int materialId) {
        trainingMaterialDao.deleteMaterial(materialId);
    }
}
