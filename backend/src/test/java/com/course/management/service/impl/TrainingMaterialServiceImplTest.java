package com.course.management.service.impl;

import com.course.management.CourseManagementApplication;
import com.course.management.dao.impl.TrainingMaterialDaoImpl;
import com.course.management.models.Feedback;
import com.course.management.models.TrainingMaterial;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CourseManagementApplication.class)
@RunWith(SpringRunner.class)
public class TrainingMaterialServiceImplTest {

    @Autowired
    private TrainingMaterialServiceImpl trainingMaterialService;

    @MockBean
    private TrainingMaterialDaoImpl trainingMaterialDao;

    TrainingMaterial trainingMaterial = new TrainingMaterial();

    @Before
    public void init(){
        trainingMaterial.setMaterialId(34);
        trainingMaterial.setCourseId(97);
        trainingMaterial.setFileName("Resume");
        trainingMaterial.setFileType("application/pdf");
        trainingMaterial.setParentId(0);
        trainingMaterial.setCurrent(true);
        trainingMaterial.setCreatedOn(new Date(System.currentTimeMillis()));
        trainingMaterial.setLastModified(new Date(System.currentTimeMillis()));
        trainingMaterial.setFileData(null);
    }

    @Test
    public void addMaterial() throws IOException {
        MultipartFile multipartFile = new MockMultipartFile("test.xlsx", new FileInputStream(new File("C:\\Users\\gaura\\Downloads\\Accolite_GaurangAthavale.pdf")));
        when(trainingMaterialDao.addMaterial(trainingMaterial, multipartFile)).thenReturn(trainingMaterial);
        assertThat(trainingMaterialService.addMaterial(trainingMaterial, multipartFile)).isEqualTo(trainingMaterial);
    }

    @Test
    public void getMaterialByCourseId() {
        List<TrainingMaterial> trainingMaterials = new ArrayList<>();
        when(trainingMaterialDao.getMaterialByCourseId(trainingMaterial.getCourseId())).thenReturn(trainingMaterials);
        assertThat(trainingMaterialService.getMaterialByCourseId(trainingMaterial.getCourseId())).isEqualTo(trainingMaterials);
    }

    @Test
    public void getMaterialPreviousVersions() {
        List<TrainingMaterial> trainingMaterials = new ArrayList<>();
        when(trainingMaterialDao.getMaterialPreviousVersions(trainingMaterial.getCourseId(), trainingMaterial.getMaterialId())).thenReturn(trainingMaterials);
        assertThat(trainingMaterialService.getMaterialPreviousVersions(trainingMaterial.getCourseId(), trainingMaterial.getMaterialId())).isEqualTo(trainingMaterials);
    }

//    @Test
//    public void deleteMaterial() {
//    }
}