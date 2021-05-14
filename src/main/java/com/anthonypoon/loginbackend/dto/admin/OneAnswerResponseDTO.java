package com.anthonypoon.loginbackend.dto.admin;

import com.anthonypoon.loginbackend.models.Answer;
import lombok.Data;

@Data
public class OneAnswerResponseDTO {
    private Long id;
    private String statement;
    private boolean isSelected;
    private boolean isCorrectAnswer;

    public static OneAnswerResponseDTO getInstance(Answer answer) {
        OneAnswerResponseDTO dto = new OneAnswerResponseDTO();
        dto.setId(answer.getId());
        dto.setStatement(answer.getStatement());
        dto.setSelected(answer.isSelected());
        dto.setCorrectAnswer(answer.isCorrectAnswer());
        return dto;
    }
}
