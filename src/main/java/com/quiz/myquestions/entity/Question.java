package com.quiz.myquestions.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "varchar(50)")
    private String title;

    @Column(columnDefinition = "int(11)")
    private int score;

    @ManyToOne
    private Quiz quiz_id;

    @Enumerated(value = EnumType.STRING)
    private QuestionType questionType;



}
