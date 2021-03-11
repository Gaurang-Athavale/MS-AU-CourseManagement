package com.course.management.rowmapper;

import com.course.management.constants.Constants;
import com.course.management.models.TrainingMaterial;
import org.springframework.jdbc.core.RowMapper;

public class TrainingMaterialRowMapper {

    public TrainingMaterialRowMapper() {
    }

    public static final RowMapper<TrainingMaterial> TrainingMaterialRowMapperLambda = (rs, rowNum) -> {

        TrainingMaterial trainingMaterial = new TrainingMaterial();

        trainingMaterial.setMaterialId(rs.getInt(Constants.MATERIAL_ID));
        trainingMaterial.setCourseId(rs.getInt(Constants.COURSE_ID));
        trainingMaterial.setFileName(rs.getString(Constants.FILE_NAME));
        trainingMaterial.setFileData(rs.getBytes(Constants.FILE_DATA));
        trainingMaterial.setCreatedOn(rs.getDate(Constants.CREATED_ON));
        trainingMaterial.setLastModified(rs.getDate(Constants.LAST_MODIFIED));
        trainingMaterial.setFileType(rs.getString(Constants.FILE_TYPE));
        trainingMaterial.setParentId(rs.getInt(Constants.PARENT_ID));

        return trainingMaterial;

    };

//    public static final RowMapper<Integer> TrainingMaterialRowMapperCountLambda = (rs, rowNum) -> {
//
//        int count = 0;
//
//
//        return count;
//
//    };
}
