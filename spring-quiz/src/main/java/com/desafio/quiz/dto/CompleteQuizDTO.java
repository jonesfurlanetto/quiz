package com.desafio.quiz.dto;

import com.desafio.quiz.model.QuizResult;
import com.desafio.quiz.model.Tag;

import java.util.List;

public class CompleteQuizDTO {

    private Long id;
    private String title;
    private List<Tag> tags;
    private List<QuestionDTO> questions;
    private List<QuizResult> quizResults;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }

    public List<QuizResult> getQuizResults() {
        return quizResults;
    }

    public void setQuizResults(List<QuizResult> quizResults) {
        this.quizResults = quizResults;
    }
}
