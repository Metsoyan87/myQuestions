package com.quiz.myquestions.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user_id;

    @ManyToOne
    private Question question_id;

    @ManyToOne
    private QuestionOption questionOption_id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_time;
}

