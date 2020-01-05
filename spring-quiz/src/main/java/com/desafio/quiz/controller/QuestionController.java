package com.desafio.quiz.controller;

import com.desafio.quiz.model.Question;
import com.desafio.quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/quiz/{quizId}/question")
    @PreAuthorize("hasRole('USER')")
    public Page<Question> getAllQuestionsByQuizId(@PathVariable (value = "quizId") Long quizId,
                                                    Pageable pageable) {
        return questionService.getAllQuestionsByQuizId(quizId, pageable);
    }

    @PostMapping("/quiz/{quizId}/question")
    @PreAuthorize("hasRole('USER')")
    public Question createQuestion(@PathVariable (value = "quizId") Long quizId,
                                 @Valid @RequestBody Question question) {
        return questionService.createQuestion(quizId, question);
    }

    @PutMapping("/quiz/{quizId}/question/{questionId}")
    @PreAuthorize("hasRole('USER')")
    public Question updateQuestion(@PathVariable (value = "quizId") Long quizId,
                                 @PathVariable (value = "questionId") Long questionId,
                                 @Valid @RequestBody Question questionRequest) {
        return questionService.updateQuestion(quizId, questionId, questionRequest);
    }

    @DeleteMapping("/quiz/{quizId}/question/{questionId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteQuestion(@PathVariable (value = "quizId") Long quizId,
                                           @PathVariable (value = "questionId") Long questionId) {
        return questionService.deleteQuestion(quizId, questionId);
    }

}
