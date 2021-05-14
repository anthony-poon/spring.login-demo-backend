package com.anthonypoon.loginbackend.interfaces.admin;

import com.anthonypoon.loginbackend.dto.admin.AllExamsResponseDTO;
import com.anthonypoon.loginbackend.dto.admin.OneExamResponseDTO;
import com.anthonypoon.loginbackend.models.Exam;
import com.anthonypoon.loginbackend.repos.ExamRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/exams")
public class AdminExamController {
    private final ExamRepository repository;
    public AdminExamController(ExamRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public AllExamsResponseDTO getAllExam() {
        List<Exam> exams = repository.findAll();
        return AllExamsResponseDTO.getInstance(exams);
    }

    @GetMapping("/{id}")
    public OneExamResponseDTO getOneExam(@PathVariable("id") Long id) {
        Exam exam = repository.findById(id).orElseThrow();
        return OneExamResponseDTO.getInstance(exam);
    }

}
