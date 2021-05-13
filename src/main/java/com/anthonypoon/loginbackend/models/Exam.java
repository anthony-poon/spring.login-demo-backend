package com.anthonypoon.loginbackend.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private AppUser appUser;

    @OneToMany( mappedBy = "exam", cascade = CascadeType.ALL )
    private List<Question> questions = new ArrayList<>();
}
