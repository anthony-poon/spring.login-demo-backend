package com.anthonypoon.loginbackend.dto.admin;

import com.anthonypoon.loginbackend.models.Question;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class OneQuestionResponseDTO {
    private Long id;
    private String statement;
    private List<OneAnswerResponseDTO> answers;

    public static OneQuestionResponseDTO getInstance(Question question) {
        OneQuestionResponseDTO dto = new OneQuestionResponseDTO();
        dto.setStatement(question.getStatement());
        dto.setId(question.getId());
        dto.setAnswers(question.getAnswers().stream().map(OneAnswerResponseDTO::getInstance).collect(Collectors.toList()));
        return dto;
    }
}
