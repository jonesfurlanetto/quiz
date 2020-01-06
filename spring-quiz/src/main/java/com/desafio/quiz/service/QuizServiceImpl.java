package com.desafio.quiz.service;

import com.desafio.quiz.dto.CompleteQuizDTO;
import com.desafio.quiz.dto.QuestionDTO;
import com.desafio.quiz.exception.ResourceNotFoundException;
import com.desafio.quiz.model.*;
import com.desafio.quiz.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private QuizResultRepository quizResultRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

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

    @Override
    public CompleteQuizDTO getCompleteQuiz(Long quizId) {
        CompleteQuizDTO completeQuizDTO = new CompleteQuizDTO();
        List<QuestionDTO> questionsDTO = new ArrayList<>();

        quizRepository.findById(quizId).map(q -> {
            completeQuizDTO.setId(q.getId());
            completeQuizDTO.setTitle(q.getTitle());
            return completeQuizDTO;
        }).orElseThrow(() -> new ResourceNotFoundException("Quiz", "Id", quizId));

        List<Tag> tags = tagRepository.findByQuizId(quizId);
        completeQuizDTO.setTags(tags);

        List<QuizResult> results = quizResultRepository.findByQuizId(quizId);
        completeQuizDTO.setQuizResults(results);

        List<Question> questions = questionRepository.findByQuizId(quizId);
        for (Question q: questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setId(q.getId());
            questionDTO.setDescription(q.getDescription());

            List<Answer> answers = answerRepository.findByQuestionId(q.getId());
            questionDTO.setAnswers(answers);

            questionsDTO.add(questionDTO);
        }
        completeQuizDTO.setQuestions(questionsDTO);

        return completeQuizDTO;

    }
}
