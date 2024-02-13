package com.quiz.myquestions.repository;

import com.quiz.myquestions.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    Optional<Question> findByTitle(String title);


}
