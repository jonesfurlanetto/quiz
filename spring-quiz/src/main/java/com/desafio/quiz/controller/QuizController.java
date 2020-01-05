package com.desafio.quiz.controller;

import com.desafio.quiz.model.Quiz;
import com.desafio.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/quiz")
public class QuizController {


    @Autowired
    private QuizService quizService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public Page<Quiz> getAllQuizzes(Pageable pageable) {
        return quizService.getAllQuizzes(pageable);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public Quiz createQuiz(@Valid @RequestBody Quiz quiz) {
        return quizService.createQuiz(quiz);
    }

    @PutMapping("/{quizId}")
    @PreAuthorize("hasRole('USER')")
    public Quiz updateQuiz(@PathVariable Long quizId, @Valid @RequestBody Quiz quizRequest) {
        return quizService.updateQuiz(quizId, quizRequest);
    }

    @DeleteMapping("/{quizId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteQuiz(@PathVariable Long quizId) {
        return quizService.deleteQuiz(quizId);
    }

}
