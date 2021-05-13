package com.anthonypoon.loginbackend.dto.user;

import com.anthonypoon.loginbackend.models.Answer;
import lombok.Data;

@Data
public class OneAnswerResponseDTO {
    private Long id;
    private String statement;
    private boolean isSelected;

    public static OneAnswerResponseDTO getInstance(Answer answer) {
        OneAnswerResponseDTO dto = new OneAnswerResponseDTO();
        dto.setId(answer.getId());
        dto.setStatement(answer.getStatement());
        dto.setSelected(answer.isSelected());
        return dto;
    }
}
