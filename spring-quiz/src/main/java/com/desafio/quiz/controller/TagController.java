package com.desafio.quiz.controller;

import com.desafio.quiz.model.Tag;
import com.desafio.quiz.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/quiz/{quizId}/tag")
    @PreAuthorize("hasRole('USER')")
    public Page<Tag> getAllTagsByQuizId(@PathVariable (value = "quizId") Long quizId,
                                        Pageable pageable) {
        return tagService.getAllTagsByQuizId(quizId, pageable);
    }

    @PostMapping("/quiz/{quizId}/tag")
    @PreAuthorize("hasRole('USER')")
    public Tag createTag(@PathVariable (value = "quizId") Long quizId,
                         @Valid @RequestBody Tag tag) {
        return tagService.createTag(quizId, tag);
    }

    @PutMapping("/quiz/{quizId}/tag/{tagId}")
    @PreAuthorize("hasRole('USER')")
    public Tag updateTag(@PathVariable (value = "quizId") Long quizId,
                                 @PathVariable (value = "tagId") Long tagId,
                                 @Valid @RequestBody Tag tagRequest) {
        return tagService.updateTag(quizId, tagId, tagRequest);
    }

    @DeleteMapping("/quiz/{quizId}/tag/{tagId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteTag(@PathVariable (value = "quizId") Long quizId,
                                       @PathVariable (value = "tagId") Long tagId) {
        return tagService.deleteTag(quizId, tagId);
    }

    @GetMapping("/mostUsedTag")
    @PreAuthorize("hasRole('USER')")
    public String getMostUsedTag() {
        return tagService.findMostUsedTag();
    }

}
