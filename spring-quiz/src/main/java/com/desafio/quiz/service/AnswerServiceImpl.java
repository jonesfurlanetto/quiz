package com.desafio.quiz.service;

import com.desafio.quiz.exception.ResourceNotFoundException;
import com.desafio.quiz.model.Answer;
import com.desafio.quiz.repository.AnswerRepository;
import com.desafio.quiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public Page<Answer> getAllAnsweresByQuizId(Long questionId, Pageable pageable) {
        return answerRepository.findByQuestionId(questionId, pageable);
    }

    @Override
    public Answer createAnswer(Long questionId, Answer answer) {
        return questionRepository.findById(questionId).map(question -> {
            answer.setQuestion(question);
            return answerRepository.save(answer);
        }).orElseThrow(() -> new ResourceNotFoundException("Question", "Id", questionId));
    }

    @Override
    public Answer updateAnswer(Long questionId, Long answerId, Answer answerRequest) {
        if(!questionRepository.existsById(questionId)) {
            throw new ResourceNotFoundException("Question", "Id", questionId);
        }

        return answerRepository.findById(answerId).map(answer -> {
            answer.setDescription(answerRequest.getDescription());
            answer.setCorrectAnswer(answerRequest.getCorrectAnswer());
            return answerRepository.save(answer);
        }).orElseThrow(() -> new ResourceNotFoundException("Answer ", "Id", answerId));
    }

    @Override
    public ResponseEntity<?> deleteAnswer(Long questionId, Long answerId) {
        return answerRepository.findByIdAndQuestionId(answerId, questionId).map(answer -> {
            answerRepository.delete(answer);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Answer não encontrado com id " + answerId + " e questionId " + questionId));
    }
}
