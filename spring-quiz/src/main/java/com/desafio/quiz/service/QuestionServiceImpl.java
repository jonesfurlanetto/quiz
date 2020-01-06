package com.desafio.quiz.service;

import com.desafio.quiz.exception.ResourceNotFoundException;
import com.desafio.quiz.model.Question;
import com.desafio.quiz.repository.QuestionRepository;
import com.desafio.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Page<Question> getAllQuestionsByQuizId(Long quizId, Pageable pageable) {
        return questionRepository.findByQuizId(quizId, pageable);
    }

    @Override
    public Question createQuestion(Long quizId, Question question) {
        return quizRepository.findById(quizId).map(quiz -> {
            question.setQuiz(quiz);
            return questionRepository.save(question);
        }).orElseThrow(() -> new ResourceNotFoundException("Quiz", "Id", quizId));
    }

    @Override
    public Question updateQuestion(Long quizId, Long questionId, Question questionRequest) {
        if(!quizRepository.existsById(quizId)) {
            throw new ResourceNotFoundException("Quiz", "Id", quizId);
        }

        return questionRepository.findById(questionId).map(question -> {
            question.setDescription(questionRequest.getDescription());
            return questionRepository.save(question);
        }).orElseThrow(() -> new ResourceNotFoundException("Question ", "Id", questionId));
    }

    @Override
    public ResponseEntity<?> deleteQuestion(Long quizId, Long questionId) {
        return questionRepository.findByIdAndQuizId(questionId, quizId).map(question -> {
            questionRepository.delete(question);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Question não encontrado com id " + questionId + " e quizId " + quizId));
    }
}
