package com.course.management.dao.impl;

import com.course.management.CourseManagementApplication;
import com.course.management.models.Feedback;
import com.course.management.models.TrainingMaterial;
import com.course.management.rowmapper.FeedbackRowMapper;
import com.course.management.rowmapper.TrainingMaterialRowMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CourseManagementApplication.class)
class TrainingMaterialDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private TrainingMaterialDaoImpl trainingMaterialDao;

    @Before
    private TrainingMaterial setTrainingMaterial(){
        TrainingMaterial trainingMaterial = new TrainingMaterial();

        trainingMaterial.setMaterialId(34);
        trainingMaterial.setCourseId(97);
        trainingMaterial.setFileName("Resume");
        trainingMaterial.setFileType("application/pdf");
        trainingMaterial.setParentId(0);
        trainingMaterial.setCurrent(true);
        trainingMaterial.setCreatedOn(new Date(System.currentTimeMillis()));
        trainingMaterial.setLastModified(new Date(System.currentTimeMillis()));
        trainingMaterial.setFileData(null);

        return trainingMaterial;
    }

    @Test
    void addMaterial() {
    }

    @Test
    void getMaterialByCourseId() {
        TrainingMaterial trainingMaterial = setTrainingMaterial();
        List<TrainingMaterial> trainingMaterials = new ArrayList<>();
        when(this.jdbcTemplate.query("SELECT * FROM trainingmaterial WHERE course_id = " + trainingMaterial.getCourseId(), TrainingMaterialRowMapper.TrainingMaterialRowMapperLambda)).thenReturn(trainingMaterials);

        List<TrainingMaterial> testMaterial=trainingMaterialDao.getMaterialByCourseId(trainingMaterial.getCourseId());
        assertEquals(trainingMaterials,testMaterial);
    }

    @Test
    void getMaterialPreviousVersions() {

    }

    @Test
    void deleteMaterial() {
    }
}