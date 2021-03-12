package com.course.management.dao.impl;

import com.course.management.dao.TrainingMaterialDao;
import com.course.management.queries.Queries;
import com.course.management.models.TrainingMaterial;
import com.course.management.rowmapper.TrainingMaterialRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class TrainingMaterialDaoImpl implements TrainingMaterialDao {

    Logger logger= LoggerFactory.getLogger(TrainingMaterialDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public TrainingMaterial addMaterial(TrainingMaterial trainingMaterial, MultipartFile file) throws IOException {

        System.out.println(trainingMaterial.getCourseId());
        if(jdbcTemplate.query(Queries.GET_COUNT_OF_MATERIAL_BY_COURSE_ID, TrainingMaterialRowMapper.TrainingMaterialRowMapperLambda, trainingMaterial.getCourseId()).size() == 0) {
            jdbcTemplate.update(Queries.ADD_MATERIAL, trainingMaterial.getCourseId(), trainingMaterial.getFileType(), trainingMaterial.getFileName(), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), file.getBytes(), 0, 1);
        }
        else{
            jdbcTemplate.update(Queries.ADD_MATERIAL, trainingMaterial.getCourseId(), trainingMaterial.getFileType(), trainingMaterial.getFileName(), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), file.getBytes(), 0, 1);

            TrainingMaterial tm = new TrainingMaterial();
            tm = jdbcTemplate.queryForObject(Queries.GET_HIGHEST_MATERIAL_ID, TrainingMaterialRowMapper.TrainingMaterialRowMapperLambda);

            jdbcTemplate.update(Queries.UPDATE_NEW_CHILD, tm.getMaterialId() , 0, trainingMaterial.getMaterialId());

        }
        logger.info("Added training material with materialId = " + trainingMaterial.getMaterialId());
        return trainingMaterial;
    }

    @Override
    public List<TrainingMaterial> getMaterialByCourseId(int courseId) {
        logger.info("Retrieved Material with courseId = " + courseId);
        return jdbcTemplate.query(Queries.GET_MATERIAL_BY_COURSE_ID, TrainingMaterialRowMapper.TrainingMaterialRowMapperLambda, courseId);
    }

    @Override
    public List<TrainingMaterial> getMaterialPreviousVersions(int courseId, int materialId) {
        System.out.println(courseId+" "+materialId);

        try {
        List<TrainingMaterial> materials = new ArrayList<TrainingMaterial>();
        TrainingMaterial tm = jdbcTemplate.queryForObject("SELECT * FROM trainingmaterial WHERE course_id = ? AND material_id = ? AND isCurrent = 1", TrainingMaterialRowMapper.TrainingMaterialRowMapperLambda, courseId, materialId);
        int parentId = tm.getParentId();
        do {
            try {
                tm = jdbcTemplate.queryForObject("SELECT * FROM trainingmaterial WHERE course_id = ? AND parent_id = ? AND isCurrent = 0", TrainingMaterialRowMapper.TrainingMaterialRowMapperLambda, courseId, tm.getMaterialId());
                materials.add(tm);
                parentId = tm.getParentId();
            }
            catch (Exception em){
                parentId = 0;
            }
            System.out.println();
        }while (parentId > 0 && tm!=null);
        logger.info("Retrieved all previous versions for courseId = " + courseId);
        return materials;
    }
    catch(Exception e){
        System.out.println("Exception");
        logger.error("Error while retrieving previous versions");
        return new ArrayList<>();
    }
    }

    @Override
    public void deleteMaterial(int materialId) {
        TrainingMaterial tm = new TrainingMaterial();
        jdbcTemplate.update(Queries.DELETE_LATEST_TRAINING_MATERIAL, materialId);
        tm = jdbcTemplate.queryForObject("SELECT * FROM trainingmaterial WHERE parent_id = ?", TrainingMaterialRowMapper.TrainingMaterialRowMapperLambda,materialId);
        jdbcTemplate.update(Queries.UPDATE_NEW_CHILD, 0, 1, tm.getMaterialId());
        logger.info("Deleted material with materialId = ", materialId);
    }
}
