package com.quiz.myquestions.repository;

import com.quiz.myquestions.entity.Answer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    Page<Answer> findAnswerById(int answerId, Pageable pageable);


}
