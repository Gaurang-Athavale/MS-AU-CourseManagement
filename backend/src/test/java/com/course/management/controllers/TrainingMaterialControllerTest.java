package com.course.management.controllers;

import com.course.management.models.Course;
import com.course.management.models.TrainingMaterial;
import com.course.management.service.TrainingMaterialService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ContentType;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TrainingMaterialController.class)
class TrainingMaterialControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

    @MockBean
    public TrainingMaterialService trainingMaterialService;

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

//    @Test
//    void addMaterial() throws IOException {
//        TrainingMaterial trainingMaterial = setTrainingMaterial();
//        String jsonString = objectMapper.writeValueAsString(trainingMaterial);
//        Mockito.when(trainingMaterialService.addMaterial(trainingMaterial.getMaterialId(),trainingMaterial.getCourseId(), trainingMaterial.getFileName(), trainingMaterial.getFileType())).thenReturn(trainingMaterial);
//        mockMvc.perform(post("/course/addCourse")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(jsonString)
//                .accept(MediaType.APPLICATION_JSON_VALUE)
//        ).andExpect(status().isOk());
//    }

    @Test
    void getMaterialByCourseId() throws Exception {
        ArrayList<TrainingMaterial> trainingMaterials = new ArrayList<>();
        TrainingMaterial trainingMaterial = setTrainingMaterial();
        trainingMaterials.add(trainingMaterial);
        Mockito.when(trainingMaterialService.getMaterialByCourseId(trainingMaterial.getCourseId())).thenReturn(trainingMaterials);
        mockMvc.perform(get("/material/getMaterial/" + trainingMaterial.getCourseId())).andExpect(status().isOk());
    }

    @Test
    void getMaterialPreviousVersions() throws Exception {
        ArrayList<TrainingMaterial> trainingMaterials = new ArrayList<>();
        TrainingMaterial trainingMaterial = setTrainingMaterial();
        trainingMaterials.add(trainingMaterial);
        Mockito.when(trainingMaterialService.getMaterialPreviousVersions(trainingMaterial.getCourseId(), trainingMaterial.getMaterialId())).thenReturn(trainingMaterials);
        mockMvc.perform(get("/material/getMaterialPreviousVersions/" + trainingMaterial.getCourseId() + "/" + trainingMaterial.getMaterialId())).andExpect(status().isOk());
    }

    @Test
    void deleteMaterial() throws Exception {
        int id = 1;
        Mockito.doNothing().when(trainingMaterialService).deleteMaterial(id);
        mockMvc.perform(delete("/material/deleteMaterial/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(String.valueOf(id))
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk());
    }
}