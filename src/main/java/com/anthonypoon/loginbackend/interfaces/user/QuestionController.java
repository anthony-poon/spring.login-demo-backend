package com.anthonypoon.loginbackend.interfaces.user;

import com.anthonypoon.loginbackend.models.Question;
import com.anthonypoon.loginbackend.repos.QuestionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/questions")
public class QuestionController {
    private final QuestionRepository repository;
    public QuestionController(QuestionRepository repository) {
        this.repository = repository;
    }

    @PutMapping("/{questionId}/answers/{answerId}")
    public ResponseEntity<Void> updateOneAnswer(@PathVariable("questionId") Long questionId, @PathVariable("answerId") Long answerId) {
        Question question = repository.findById(questionId).orElseThrow();
        question.getAnswers().forEach(answer -> {
            answer.setSelected(answer.getId().equals(answerId));
        });
        repository.saveAndFlush(question);
        return ResponseEntity.ok().build();
    }
}
