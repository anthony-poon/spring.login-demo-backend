package com.anthonypoon.loginbackend.repos;

import com.anthonypoon.loginbackend.models.AppUser;
import com.anthonypoon.loginbackend.models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository  extends JpaRepository<Exam, Long> {
    List<Exam> findAllByAppUser(AppUser appUser);
}
