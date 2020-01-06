package com.desafio.quiz.controller;

import com.desafio.quiz.dto.AnswerQuizDTO;
import com.desafio.quiz.model.QuizResult;
import com.desafio.quiz.service.QuizResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class QuizResultController {

    @Autowired
    private QuizResultService quizResultService;

    @GetMapping("/quiz/{quizId}/quizResult")
    public Page<QuizResult> getAllQuizResultsByQuizId(@PathVariable (value = "quizId") Long quizId,
                                                      Pageable pageable) {
        return quizResultService.getAllQuizResultsByQuizId(quizId, pageable);
    }

    @PostMapping("/quiz/{quizId}/quizResult")
    @PreAuthorize("hasRole('USER')")
    public QuizResult createQuizResult(@PathVariable (value = "quizId") Long quizId,
                                 @Valid @RequestBody QuizResult quizResult) {
        return quizResultService.createQuizResult(quizId, quizResult);
    }

    @PutMapping("/quiz/{quizId}/quizResult/{quizResultId}")
    @PreAuthorize("hasRole('USER')")
    public QuizResult updateQuizResult(@PathVariable (value = "quizId") Long quizId,
                                 @PathVariable (value = "quizResultId") Long quizResultId,
                                 @Valid @RequestBody QuizResult quizResultRequest) {
        return quizResultService.updateQuizResult(quizId, quizResultId, quizResultRequest);
    }

    @DeleteMapping("/quiz/{quizId}/quizResult/{quizResultId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteQuizResult(@PathVariable (value = "quizId") Long quizId,
                                           @PathVariable (value = "quizResultId") Long quizResultId) {
        return quizResultService.deleteQuizResult(quizId, quizResultId);
    }

    @GetMapping("/quiz/mostAnswer")
    @PreAuthorize("hasRole('USER')")
    public String getMostAnswerQuiz() {
        return quizResultService.getMostAnswerQuiz();
    }

    @GetMapping("/quiz/{quizId}/quizResult/avg")
    @PreAuthorize("hasRole('USER')")
    public Double getAvgQuiz(@PathVariable (value = "quizId") Long quizId) {
        return quizResultService.getAvgQuiz(quizId);
    }

    @PostMapping("/quiz/answer")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> answerQuiz(@Valid @RequestBody AnswerQuizDTO answerQuizDTO, Principal principal) {
        return quizResultService.answerQuiz(answerQuizDTO, principal.getName());
    }

}
