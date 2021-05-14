package com.anthonypoon.loginbackend.dto.admin;

import com.anthonypoon.loginbackend.models.Answer;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class AllAnswersResponseDTO {
    private List<Long> answerIds;
    public static AllAnswersResponseDTO getInstance(List<Answer> answers) {
        AllAnswersResponseDTO dto = new AllAnswersResponseDTO();
        dto.setAnswerIds(answers.stream().map(Answer::getId).collect(Collectors.toList()));
        return dto;
    }
}
