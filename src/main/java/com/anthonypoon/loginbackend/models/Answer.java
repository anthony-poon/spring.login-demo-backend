package com.anthonypoon.loginbackend.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Question question;

    // TODO
}
