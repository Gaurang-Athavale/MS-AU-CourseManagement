package com.course.management.dao.impl;

import com.course.management.dao.TrainingMaterialDao;
import com.course.management.queries.Queries;
import com.course.management.models.TrainingMaterial;
import com.course.management.rowmapper.TrainingMaterialRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Repository
public class TrainingMaterialDaoImpl implements TrainingMaterialDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public TrainingMaterial addMaterial(TrainingMaterial trainingMaterial, MultipartFile file) throws IOException {

        System.out.println(trainingMaterial.getCourseId());
        if(jdbcTemplate.query(Queries.GET_COUNT_OF_MATERIAL_BY_COURSE_ID, TrainingMaterialRowMapper.TrainingMaterialRowMapperLambda, trainingMaterial.getCourseId()).size() == 0) {
            jdbcTemplate.update(Queries.ADD_MATERIAL, trainingMaterial.getCourseId(), trainingMaterial.getFileType(), trainingMaterial.getFileName(), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), file.getBytes(), 0, 1);
        }
        else{

            TrainingMaterial tm1 = new TrainingMaterial();
            tm1 = jdbcTemplate.queryForObject(Queries.GET_HIGHEST_MATERIAL_WITH_COURSE_ID, TrainingMaterialRowMapper.TrainingMaterialRowMapperLambda, trainingMaterial.getCourseId());
            // Update the tm1's parentId and isCurrent
            // and then add new material

            jdbcTemplate.update(Queries.ADD_MATERIAL, trainingMaterial.getCourseId(), trainingMaterial.getFileType(), trainingMaterial.getFileName(), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), file.getBytes(), 0, 1);

            TrainingMaterial tm = new TrainingMaterial();
            tm = jdbcTemplate.queryForObject(Queries.GET_HIGHEST_MATERIAL_ID, TrainingMaterialRowMapper.TrainingMaterialRowMapperLambda);



            jdbcTemplate.update(Queries.UPDATE_NEW_CHILD, tm.getMaterialId() , 0, tm1.getMaterialId());

        }

        return trainingMaterial;
    }

    @Override
    public List<TrainingMaterial> getMaterialByCourseId(int courseId) {
        return jdbcTemplate.query(Queries.GET_MATERIAL_BY_COURSE_ID, TrainingMaterialRowMapper.TrainingMaterialRowMapperLambda, courseId);
    }

    @Override
    public List<TrainingMaterial> getMaterialPreviousVersions(int courseId) {
        return jdbcTemplate.query(Queries.GET_ALL_PREVIOUS_VERSIONS_BY_COURSE_ID, TrainingMaterialRowMapper.TrainingMaterialRowMapperLambda, courseId);
    }

    @Override
    public void deleteMaterial(int courseId) {
        TrainingMaterial tm = new TrainingMaterial();
        jdbcTemplate.update(Queries.DELETE_LATEST_TRAINING_MATERIAL, courseId);
        tm = jdbcTemplate.queryForObject(Queries.GET_HIGHEST_MATERIAL_WITH_COURSE_ID, TrainingMaterialRowMapper.TrainingMaterialRowMapperLambda,courseId);
        jdbcTemplate.update(Queries.UPDATE_NEW_CHILD, 0, 1, tm.getMaterialId());
    }
}
