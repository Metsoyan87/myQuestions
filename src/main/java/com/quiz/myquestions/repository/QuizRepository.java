package com.quiz.myquestions.repository;

import com.quiz.myquestions.entity.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {

    Page<Quiz> findQuizById(int quizId, Pageable pageable);

    Optional<Quiz> findByTitle(String title);

    Quiz findById(int  id);


}
