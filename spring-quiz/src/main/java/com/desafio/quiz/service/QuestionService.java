package com.desafio.quiz.service;

import com.desafio.quiz.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface QuestionService {
    Page<Question> getAllQuestionsByQuizId(Long quizId, Pageable pageable);
    Question createQuestion(Long quizId, Question question);
    Question updateQuestion(Long quizId, Long questionId, Question questionRequest);
    ResponseEntity<?> deleteQuestion(Long quizId, Long questionId);
}
