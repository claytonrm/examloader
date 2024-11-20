package com.demo.examloader.controller;

import com.demo.examloader.domain.ExamDTO;
import com.demo.examloader.service.ExamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureDataJpa
public class ExamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExamService service;

    @Value("classpath:samples/exams-response.json")
    private Resource examsResponseSampleResource;

    @Value("classpath:exercise.csv")
    private Resource examsSampleCSVResource;

    @Test
    void shouldReturnAllExams() throws Exception {
        final String examAsJSON = new String(Files.readAllBytes(examsResponseSampleResource.getFile().toPath()));

        given(service.findAll()).willReturn(List.of(
                new ExamDTO("ZIB", "ZIB001", "271636001", "Polsslag regelmatig", "The long description is necessary", "01-01-2019", "", "1"),
                new ExamDTO("ZIB", "ZIB001", "61086009", "Polsslag onregelmatig", "", "01-01-2019", "", "2")
        ));

        this.mockMvc.perform(get("/exams"))
                .andExpect(status().isOk())
                .andExpect(content().json(examAsJSON));

        verify(service).findAll();
    }

    @Test
    void shouldDeleteAllExams() throws Exception {
        this.mockMvc.perform(delete("/exams"))
                .andExpect(status().isNoContent());

        verify(service).deleteAll();
    }

    @Test
    void shouldFindExamsByCode() throws Exception {
        final ExamDTO expectedExam = new ExamDTO("ZIB", "ZIB001", "271636001", "Polsslag regelmatig", "The long description is necessary", "01-01-2019", "", "1");
        final String expectedExamAsJSON = new ObjectMapper().writeValueAsString(List.of(expectedExam));

        given(service.findByCode(any())).willReturn(List.of(expectedExam));

        this.mockMvc.perform(get("/exams?code=271636001"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedExamAsJSON));

        verify(service).findByCode("271636001");
    }

    @Test
    @Disabled("This test is disabled because it requires a file to be uploaded")
    void shouldUploadExams() throws Exception {
        this.mockMvc.perform(post("/exams/upload")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .queryParam("file", "exercise.csv")
        ).andExpect(status().isNoContent());

        verify(service).create(any(MultipartFile.class));
    }

}
