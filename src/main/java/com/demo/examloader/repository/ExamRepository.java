package com.demo.examloader.repository;

import com.demo.examloader.domain.ExamModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<ExamModel, Long> {

    List<ExamModel> findByCode(final String code);

}
