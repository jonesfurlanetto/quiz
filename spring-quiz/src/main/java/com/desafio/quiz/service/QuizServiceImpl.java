package com.desafio.quiz.service;

import com.desafio.quiz.exception.ResourceNotFoundException;
import com.desafio.quiz.model.Quiz;
import com.desafio.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public Page<Quiz> getAllQuizzes(Pageable pageable) {
        return quizRepository.findAll(pageable);
    }

    @Override
    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Long quizId, Quiz quizRequest) {
        return quizRepository.findById(quizId).map(quiz -> {
            quiz.setTitle(quizRequest.getTitle());
            quiz.setImage(quizRequest.getImage());
            return quizRepository.save(quiz);
        }).orElseThrow(() -> new ResourceNotFoundException("Quiz", "Id", quizId));
    }

    @Override
    public ResponseEntity<?> deleteQuiz(Long quizId) {
        return quizRepository.findById(quizId).map(quiz -> {
            quizRepository.delete(quiz);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Quiz", "Id", quizId));
    }
}
