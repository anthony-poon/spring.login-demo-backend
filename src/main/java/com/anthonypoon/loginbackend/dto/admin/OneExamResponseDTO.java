package com.anthonypoon.loginbackend.dto.admin;

import com.anthonypoon.loginbackend.models.Exam;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class OneExamResponseDTO {
    private Long id;
    private String name;
    private Long score;
    private List<OneQuestionResponseDTO> questions;

    public static OneExamResponseDTO getInstance(Exam exam) {
        Long score = exam.getQuestions().stream()
                .filter(
                        question -> question.getAnswers().stream().anyMatch(
                                answer -> answer.isCorrectAnswer() && answer.isSelected()
                        )
                )
                .count();
        OneExamResponseDTO dto = new OneExamResponseDTO();
        dto.setId(exam.getId());
        dto.setName(exam.getName());
        dto.setQuestions(exam.getQuestions().stream().map(OneQuestionResponseDTO::getInstance).collect(Collectors.toList()));
        dto.setScore(score);
        return dto;
    }
}
