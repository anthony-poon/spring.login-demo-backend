package com.anthonypoon.loginbackend.dto.admin;

import com.anthonypoon.loginbackend.models.Answer;
import lombok.Data;

@Data
public class OneAnswerResponseDTO {
    public static OneAnswerResponseDTO getInstance(Answer answer) {
        OneAnswerResponseDTO dto = new OneAnswerResponseDTO();
        // TODO: Fill in the detail
        return dto;
    }
}
