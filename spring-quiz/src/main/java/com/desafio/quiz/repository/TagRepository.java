package com.desafio.quiz.repository;

import com.desafio.quiz.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    Page<Tag> findByQuizId(Long quizId, Pageable pageable);
    Optional<Tag> findByIdAndQuizId(Long id, Long quizId);
    Tag findByDescriptionAndQuizId(String description, Long quizId);
    @Query(value = "SELECT t.description FROM tags t group by t.description order by count(t) desc limit 1",
           nativeQuery = true)
    String findMostUsedTag();

}