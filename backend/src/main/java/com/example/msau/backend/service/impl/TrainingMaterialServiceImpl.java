package com.example.msau.backend.service.impl;

import com.example.msau.backend.dao.TrainingMaterialDao;
import com.example.msau.backend.models.TrainingMaterial;
import com.example.msau.backend.service.TrainingMaterialService;
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
}
