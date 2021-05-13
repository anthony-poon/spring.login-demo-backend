package com.anthonypoon.loginbackend;

import com.anthonypoon.loginbackend.models.AppUser;
import com.anthonypoon.loginbackend.models.Exam;
import com.anthonypoon.loginbackend.models.Answer;
import com.anthonypoon.loginbackend.models.Question;
import com.anthonypoon.loginbackend.repos.AppUserRepository;
import com.anthonypoon.loginbackend.repos.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataLoader {
    private static final List<String> LOREM_EXAM = Arrays.asList(
            "Exam 1",
            "Exam 2",
            "Exam 3",
            "Exam 4"
    );
    private static final List<String> LOREM_QUESTIONS = Arrays.asList(
            "Question statement 1",
            "Question statement 2",
            "Question statement 3",
            "Question statement 4"
    );
    private static final List<String> LOREM_OPTIONS = Arrays.asList(
            "Answer 1",
            "Answer 2",
            "Answer 3",
            "Answer 4"
    );
    private final AppUserRepository userRepo;
    private final PasswordEncoder encoder;
    private final ExamRepository examRepo;
    @Autowired
    private DataLoader(
            AppUserRepository appUserRepository,
            PasswordEncoder passwordEncoder,
            ExamRepository examRepo
    ) {
        this.userRepo = appUserRepository;
        this.encoder = passwordEncoder;
        this.examRepo = examRepo;
        AppUser admin = loadAdmin();
        AppUser user = loadUser();
        List<Exam> exams = loadExams(user);
        examRepo.saveAll(exams);
        examRepo.flush();
    }

    private AppUser loadUser() {
        AppUser user = AppUser.builder()
                .roles(Collections.singleton("ROLE_USER"))
                .username("user")
                .password(encoder.encode("password"))
                .build();
        userRepo.saveAndFlush(user);
        return user;
    }

    private AppUser loadAdmin() {
        AppUser admin = AppUser.builder()
                .roles(Collections.singleton("ROLE_ADMIN"))
                .username("admin")
                .password(encoder.encode("password"))
                .build();
        userRepo.saveAndFlush(admin);
        return admin;
    }

    private List<Exam> loadExams(AppUser user) {
        return DataLoader.LOREM_EXAM.stream().map(s -> {
            Exam exam = new Exam();
            exam.setAppUser(user);
            exam.setName(s);
            exam.setQuestions(loadQuestions(exam));
            return exam;
        }).collect(Collectors.toList());
    }

    private List<Question> loadQuestions(Exam exam) {
        return DataLoader.LOREM_QUESTIONS.stream().map(s -> {
            Question question = new Question();
            question.setExam(exam);
            question.setStatement(s);
            List<Answer> answers = loadOptions(question);
            answers.get(0).setCorrectAnswer(true);
            question.setAnswers(answers);
            return question;
        }).collect(Collectors.toList());
    }

    private List<Answer> loadOptions(Question question) {
        return DataLoader.LOREM_OPTIONS.stream().map(s -> {
            Answer answer = new Answer();
            answer.setQuestion(question);
            answer.setStatement(s);
            return answer;
        }).collect(Collectors.toList());
    }
}
