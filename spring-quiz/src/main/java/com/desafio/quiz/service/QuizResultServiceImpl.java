package com.desafio.quiz.service;

import com.desafio.quiz.exception.ResourceNotFoundException;
import com.desafio.quiz.model.QuizResult;
import com.desafio.quiz.repository.QuizRepository;
import com.desafio.quiz.repository.QuizResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class QuizResultServiceImpl implements QuizResultService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizResultRepository quizResultRepository;

    @Override
    public Page<QuizResult> getAllQuizResultsByQuizId(Long quizId, Pageable pageable) {
        return quizResultRepository.findByQuizId(quizId, pageable);
    }

    @Override
    public QuizResult createQuizResult(Long quizId, QuizResult quizResult) {
        return quizRepository.findById(quizId).map(quiz -> {
            quizResult.setQuiz(quiz);
            return quizResultRepository.save(quizResult);
        }).orElseThrow(() -> new ResourceNotFoundException("Quiz", "Id", quizId));
    }

    @Override
    public QuizResult updateQuizResult(Long quizId, Long quizResultId, QuizResult quizResultRequest) {
        if(!quizRepository.existsById(quizId)) {
            throw new ResourceNotFoundException("Quiz", "Id", quizId);
        }

        return quizResultRepository.findById(quizResultId).map(quizResult -> {
            quizResult.setCorrectAnsweres(quizResultRequest.getCorrectAnsweres());
            quizResult.setName(quizResultRequest.getName());
            return quizResultRepository.save(quizResult);
        }).orElseThrow(() -> new ResourceNotFoundException("QuizResult ", "Id", quizResultId));
    }

    @Override
    public ResponseEntity<?> deleteQuizResult(Long quizId, Long quizResultId) {
        return quizResultRepository.findByIdAndQuizId(quizResultId, quizId).map(quizResult -> {
            quizResultRepository.delete(quizResult);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("QuizResult não encontrado com id " + quizResultId + " e quizId " + quizId));
    }

    @Override
    public String getMostAnswerQuiz() {
        return quizResultRepository.findMostAnswerQuiz();
    }

    @Override
    public Double getAvgQuiz(Long quizId) {
        return quizResultRepository.getAvgQuizResult(quizId);
    }
}
