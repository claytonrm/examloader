package com.demo.examloader.service;

import com.demo.examloader.domain.ExamDTO;
import com.demo.examloader.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ExamService {

    private final ExamRepository repository;
    private final ExamCSVService csvService;

    @Autowired
    public ExamService(final ExamRepository repository, final ExamCSVService csvService) {
        this.repository = repository;
        this.csvService = csvService;
    }

    public void create(final MultipartFile file) {
        try {
            csvService.read(file.getInputStream())
                    .forEach(this::create);
        } catch (IOException e) {
            throw new IllegalArgumentException("File could not be read", e);
        }
    }

    public void create(final ExamDTO exam) {
        repository.save(exam.toModel());
    }

    public List<ExamDTO> findAll() {
        return repository.findAll().stream()
                .map(ExamDTO::fromModel)
                .toList();
    }

    public List<ExamDTO> findByCode(final String code) {
        return repository.findByCode(code)
                .stream().map(ExamDTO::fromModel)
                .toList();
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
