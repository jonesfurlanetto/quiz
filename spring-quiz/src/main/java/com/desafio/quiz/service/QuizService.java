package com.desafio.quiz.service;

import com.desafio.quiz.model.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface QuizService {
    Page<Quiz> getAllQuizzes(Pageable pageable);
    Quiz createQuiz(Quiz quiz);
    Quiz updateQuiz(Long quizId, Quiz quizRequest);
    ResponseEntity<?> deleteQuiz(Long quizId);
}
