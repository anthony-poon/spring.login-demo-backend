package com.anthonypoon.loginbackend.dto.user;

import com.anthonypoon.loginbackend.models.Exam;
import com.anthonypoon.loginbackend.models.Question;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class OneExamResponseDTO {
    private Long id;
    private String name;
    private List<OneQuestionResponseDTO> questions;

    public static OneExamResponseDTO getInstance(Exam exam) {
        OneExamResponseDTO dto = new OneExamResponseDTO();
        dto.setId(exam.getId());
        dto.setName(exam.getName());
        dto.setQuestions(exam.getQuestions().stream().map(OneQuestionResponseDTO::getInstance).collect(Collectors.toList()));
        return dto;
    }
}
