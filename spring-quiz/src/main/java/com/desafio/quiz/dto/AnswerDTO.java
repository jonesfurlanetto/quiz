package com.desafio.quiz.dto;

public class AnswerDTO {

    private String description;
    private boolean correctAnswer = false;
    private boolean markedAnswer = false;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public boolean isMarkedAnswer() {
        return markedAnswer;
    }

    public void setMarkedAnswer(boolean markedAnswer) {
        this.markedAnswer = markedAnswer;
    }
}
