package com.anthonypoon.loginbackend.dto.admin;

import com.anthonypoon.loginbackend.models.Exam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllExamsResponseDTO {
    private List<Long> examIds;
    public static AllExamsResponseDTO getInstance(List<Exam> exams) {
        return AllExamsResponseDTO.builder()
                .examIds(exams.stream().map(Exam::getId).collect(Collectors.toList()))
                .build();
    }
}
