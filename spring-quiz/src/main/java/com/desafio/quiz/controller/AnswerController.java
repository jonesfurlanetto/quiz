package com.desafio.quiz.controller;

import com.desafio.quiz.model.Answer;
import com.desafio.quiz.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping("/question/{questionId}/answer")
    @PreAuthorize("hasRole('USER')")
    public Page<Answer> getAllAnsweresByQuizId(@PathVariable (value = "questionId") Long questionId,
                                               Pageable pageable) {
        return answerService.getAllAnsweresByQuizId(questionId, pageable);
    }

    @PostMapping("/question/{questionId}/answer")
    @PreAuthorize("hasRole('USER')")
    public Answer createAnswer(@PathVariable (value = "questionId") Long questionId,
                                 @Valid @RequestBody Answer answer) {
        return answerService.createAnswer(questionId, answer);
    }

    @PutMapping("/question/{questionId}/answer/{answerId}")
    @PreAuthorize("hasRole('USER')")
    public Answer updateAnswer(@PathVariable (value = "questionId") Long questionId,
                                 @PathVariable (value = "answerId") Long answerId,
                                 @Valid @RequestBody Answer answerRequest) {
        return answerService.updateAnswer(questionId, answerId, answerRequest);
    }

    @DeleteMapping("/question/{questionId}/answer/{answerId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteAnswer(@PathVariable (value = "questionId") Long questionId,
                                           @PathVariable (value = "answerId") Long answerId) {
        return answerService.deleteAnswer(questionId, answerId);
    }
}
