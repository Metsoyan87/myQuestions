package com.quiz.myquestions.entity;//package com.myQuiz.accessingdatajpa.entity;

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
@Table(name = "questionOption")
public class QuestionOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Question question_id;

    @Column(columnDefinition = "varchar(50)")
    private String title;

    private boolean isCorrect;
}
