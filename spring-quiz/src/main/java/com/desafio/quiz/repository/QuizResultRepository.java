package com.desafio.quiz.repository;

import com.desafio.quiz.model.QuizResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {

    Page<QuizResult> findByQuizId(Long quizId, Pageable pageable);
    Optional<QuizResult> findByIdAndQuizId(Long id, Long quizId);
    List<QuizResult> findByQuizId(Long quizId);
    @Query(value = "SELECT q.title FROM quizresults qr join quiz q on q.id = qr.quiz_id group by qr.quiz_id, q.title order by count(qr) desc limit 1", nativeQuery = true)
    String findMostAnswerQuiz();
    @Query(value = "SELECT avg(qr.correct_answeres) as media FROM quizresults qr where qr.quiz_id = :quizId group by qr.quiz_id order by avg(qr.correct_answeres) desc limit 1", nativeQuery = true)
    Double getAvgQuizResult(@Param("quizId") Long quizId);

}