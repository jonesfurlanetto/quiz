package com.desafio.quiz.service;

import com.desafio.quiz.dto.AnswerQuizDTO;
import com.desafio.quiz.model.QuizResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface QuizResultService {
    Page<QuizResult> getAllQuizResultsByQuizId(Long quizId, Pageable pageable);
    QuizResult createQuizResult(Long quizId, QuizResult quizResult);
    QuizResult updateQuizResult(Long quizId, Long quizResultId, QuizResult quizResultRequest);
    ResponseEntity<?> deleteQuizResult(Long quizId, Long quizResultId);
    String getMostAnswerQuiz();
    Double getAvgQuiz(Long quizId);
    ResponseEntity<?> answerQuiz(AnswerQuizDTO answerQuizDTO, String userName);

}
