package com.quiz.myquestions.repository;

import com.quiz.myquestions.entity.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface QuestionOptionRepository extends JpaRepository<QuestionOption, Integer> {

    Optional<QuestionOption> findByTitle(String title);

}
