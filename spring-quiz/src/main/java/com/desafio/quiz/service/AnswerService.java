package com.desafio.quiz.service;

import com.desafio.quiz.model.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AnswerService {
    Page<Answer> getAllAnsweresByQuizId(Long questionId, Pageable pageable);
    Answer createAnswer(Long questionId, Answer answer);
    Answer updateAnswer(Long questionId, Long answerId, Answer answerRequest);
    ResponseEntity<?> deleteAnswer(Long questionId, Long answerId);
}
