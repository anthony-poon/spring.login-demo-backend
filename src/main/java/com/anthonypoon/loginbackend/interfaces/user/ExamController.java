package com.anthonypoon.loginbackend.interfaces.user;

import com.anthonypoon.loginbackend.dto.user.AllExamsResponseDTO;
import com.anthonypoon.loginbackend.dto.user.OneExamResponseDTO;
import com.anthonypoon.loginbackend.models.AppUser;
import com.anthonypoon.loginbackend.models.Exam;
import com.anthonypoon.loginbackend.repos.ExamRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user/exams")
public class ExamController {
    private final ExamRepository repository;

    public ExamController(ExamRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public AllExamsResponseDTO getAllExam(@AuthenticationPrincipal AppUser user) {
        List<Exam> exams = repository.findAllByAppUser(user);
        return AllExamsResponseDTO.getInstance(exams);
    }

    @GetMapping("/{id}")
    public OneExamResponseDTO getOneExam(@PathVariable("id") Long id) {
        Exam exam = repository.findById(id).orElseThrow();
        return OneExamResponseDTO.getInstance(exam);
    }
}
