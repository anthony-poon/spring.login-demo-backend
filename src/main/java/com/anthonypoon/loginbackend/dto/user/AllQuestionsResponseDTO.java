package com.anthonypoon.loginbackend.dto.user;

import com.anthonypoon.loginbackend.models.Question;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class AllQuestionsResponseDTO {
    private List<Long> questionIds;

    public static AllQuestionsResponseDTO getInstance(List<Question> questions) {
        AllQuestionsResponseDTO dto = new AllQuestionsResponseDTO();
        dto.setQuestionIds(questions.stream().map(Question::getId).collect(Collectors.toList()));
        return dto;
    }
}
