package com.desafio.quiz.repository;

import com.desafio.quiz.model.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    Page<Answer> findByQuestionId(Long questionId, Pageable pageable);
    Optional<Answer> findByIdAndQuestionId(Long id, Long questionId);
}
