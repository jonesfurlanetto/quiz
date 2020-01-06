package com.desafio.quiz.service;

import com.desafio.quiz.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface TagService {
    Page<Tag> getAllTagsByQuizId(Long quizId, Pageable pageable);
    Tag createTag(Long quizId, Tag tag);
    Tag updateTag(Long quizId, Long tagId, Tag tagRequest);
    ResponseEntity<?> deleteTag(Long quizId, Long tagId);
    String findMostUsedTag();
}
