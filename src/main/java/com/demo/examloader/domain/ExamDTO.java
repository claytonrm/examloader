package com.demo.examloader.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class ExamDTO {

    private String source;
    private String codeListCode;
    private String code;
    private String displayValue;
    private String longDescription;
    private String fromDate;
    private String toDate;
    private String sortingPriority;

    public ExamModel toModel() {
        return ExamModel.builder()
                .source(this.source)
                .codeListCode(this.codeListCode)
                .code(this.code)
                .displayValue(this.displayValue)
                .longDescription(this.longDescription)
                .fromDate(getExtractedDate(this.fromDate))
                .toDate(getExtractedDate(this.toDate))
                .sortingPriority(this.sortingPriority)
                .build();
    }

    public static ExamDTO fromModel(final ExamModel model) {
        return new ExamDTO(
                model.getSource(),
                model.getCodeListCode(),
                model.getCode(),
                model.getDisplayValue(),
                model.getLongDescription(),
                getExtractDateAsString(model.getFromDate()),
                getExtractDateAsString(model.getToDate()),
                model.getSortingPriority()
        );
    }

    private static String getExtractDateAsString(LocalDate date) {
        return Optional.ofNullable(date)
                .map(nonNullDate -> nonNullDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .orElse(null);
    }

    private LocalDate getExtractedDate(final String rawDate) {
        return Optional.ofNullable(rawDate)
                .filter(date -> !date.isEmpty())
                .map(nonNullDate -> LocalDate.parse(nonNullDate, DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .orElse(null);
    }

}
