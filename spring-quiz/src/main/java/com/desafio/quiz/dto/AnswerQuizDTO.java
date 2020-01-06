package com.desafio.quiz.dto;

import java.util.List;

public class AnswerQuizDTO {

    private Long quizId;
    private String title;
    private List<AnswerDTO> answersDTO;

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AnswerDTO> getAnswersDTO() {
        return answersDTO;
    }

    public void setAnswersDTO(List<AnswerDTO> answersDTO) {
        this.answersDTO = answersDTO;
    }
}
