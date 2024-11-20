package com.demo.examloader.service;

import com.demo.examloader.domain.ExamDTO;
import com.demo.examloader.repository.ExamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ExamServiceTest {

    @Autowired
    private ExamService service;

    @MockBean
    private ExamRepository repository;

    @Test
    void shouldCallRepositoryToCreateExam() {
        final ExamDTO exam = new ExamDTO(
                "ZIB",
                "ZIB001",
                "271636001",
                "Polsslag regelmatig",
                "The long description is necessary",
                "01-01-2019",
                "",
                "1"
        );

        service.create(exam);

        verify(repository).save(exam.toModel());
    }

    @Test
    void shouldCallRepositoryToFetchAllExams() {
        final List<ExamDTO> expectedExams = List.of(
                new ExamDTO("ZIB","ZIB003","415929009","Inguinale temperatuur (via de lies)","","01-01-2019", null,""),
                new ExamDTO("ZIB","ZIB004","415929009","Inguinale temperatuur (via de lies)","","01-01-2019",null,"")
        );
        given(repository.findAll()).willReturn(expectedExams.stream().map(ExamDTO::toModel).toList());

        final List<ExamDTO> actual = service.findAll();

        assertThat(actual).hasSize(2);
        assertThat(actual).hasSameElementsAs(expectedExams);
    }

    @Test
    void shouldCallRepositoryToGetExamByCode() {
        final List<ExamDTO> expectedExams = List.of(new ExamDTO(
                "ZIB",
                "ZIB005",
                "415929009",
                "Inguinale temperatuur (via de lies)",
                "",
                "01-01-2019",
                null,
                ""
        ));
        given(repository.findByCode("ZIB888")).willReturn(expectedExams.stream().map(ExamDTO::toModel).toList());

        final List<ExamDTO> actual = service.findByCode("ZIB888");

        assertThat(actual).isEqualTo(expectedExams);
    }

    @Test
    void shouldReturnEmptyListWhenExamIsNotFound() {
        given(repository.findByCode("ZIB888")).willReturn(Collections.emptyList());

        final List<ExamDTO> actual = service.findByCode("ZIB888");

        assertThat(actual).isEqualTo(Collections.emptyList());
    }

    @Test
    void shouldCallRepositoryToDeleteAllExams() {
        service.deleteAll();
        verify(repository).deleteAll();
    }

}
