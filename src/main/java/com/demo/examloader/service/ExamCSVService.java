package com.demo.examloader.service;

import com.demo.examloader.domain.ExamDTO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExamCSVService {

    public List<ExamDTO> read(final InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             final CSVParser parser = new CSVParser(fileReader, CSVFormat.DEFAULT
                     .withFirstRecordAsHeader()
                     .withIgnoreHeaderCase()
                     .withTrim())
        ) {

            final Iterable<CSVRecord> csvRecords = parser.getRecords();

            final List<ExamDTO> exams = new ArrayList<>();
            csvRecords.forEach(csvRecord -> {
                final ExamDTO exam = new ExamDTO(
                        csvRecord.get("source"),
                        csvRecord.get("codeListCode"),
                        csvRecord.get("code"),
                        csvRecord.get("displayValue"),
                        csvRecord.get("longDescription"),
                        csvRecord.get("fromDate"),
                        csvRecord.get("toDate"),
                        csvRecord.get("sortingPriority")
                );
                exams.add(exam);
            });
            return exams;

        } catch (IOException e) {
            throw new RuntimeException("Fail to parse CSV file: " + e.getMessage());
        }
    }

}
