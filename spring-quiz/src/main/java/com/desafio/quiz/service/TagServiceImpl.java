package com.desafio.quiz.service;

import com.desafio.quiz.exception.BadRequestException;
import com.desafio.quiz.exception.ResourceNotFoundException;
import com.desafio.quiz.model.Tag;
import com.desafio.quiz.repository.QuizRepository;
import com.desafio.quiz.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Page<Tag> getAllTagsByQuizId(Long quizId, Pageable pageable) {
        return tagRepository.findByQuizId(quizId, pageable);
    }

    @Override
    public Tag createTag(Long quizId, Tag tag) {

        if(tagRepository.findByDescriptionAndQuizId(tag.getDescription(), quizId) != null) {
            throw new BadRequestException("Já existe a tag '" + tag.getDescription() + "' cadastrada para o quizId: " + quizId);
        }

        return quizRepository.findById(quizId).map(quiz -> {
            tag.setQuiz(quiz);
            return tagRepository.save(tag);
        }).orElseThrow(() -> new ResourceNotFoundException("Quiz", "Id", quizId));
    }

    @Override
    public Tag updateTag(Long quizId, Long tagId, Tag tagRequest) {
        if(!quizRepository.existsById(quizId)) {
            throw new ResourceNotFoundException("Quiz", "Id", quizId);
        }

        if(tagRepository.findByDescriptionAndQuizId(tagRequest.getDescription(), quizId) != null) {
            throw new BadRequestException("Já existe a tag '" + tagRequest.getDescription() + "' cadastrada para o quizId: " + quizId);
        }

        return tagRepository.findById(tagId).map(tag -> {
            tag.setDescription(tagRequest.getDescription());
            return tagRepository.save(tag);
        }).orElseThrow(() -> new ResourceNotFoundException("Tag ", "Id", tagId));
    }

    @Override
    public ResponseEntity<?> deleteTag(Long quizId, Long tagId) {
        return tagRepository.findByIdAndQuizId(tagId, quizId).map(tag -> {
            tagRepository.delete(tag);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Tag não encontrado com id " + tagId + " e quizId " + quizId));
    }

    @Override
    public String findMostUsedTag() {
        return tagRepository.findMostUsedTag();
    }
}
