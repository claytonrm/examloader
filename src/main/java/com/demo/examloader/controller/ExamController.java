package com.demo.examloader.controller;

import com.demo.examloader.domain.ExamDTO;
import com.demo.examloader.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/exams")
public class ExamController {

    private final ExamService service;

    @Autowired
    public ExamController(final ExamService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public ResponseEntity<Void> uploadExams(@RequestParam final MultipartFile file) {
        service.create(file);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ExamDTO>> getAllExams() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(params = "code")
    public ResponseEntity<List<ExamDTO>> getExamsByCode(@RequestParam final String code) {
        return ResponseEntity.ok().body(service.findByCode(code));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllExams() {
        service.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
