package com.test.anu.repository;

import com.test.anu.model.WordContent;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JlptRepository extends JpaRepository<WordContent, Long> {


    Page<WordContent> findByLevel(String level, Pageable pageable);

    @Cacheable(value = "jlptCache", key = "#level")
    default List<WordContent> findAllByLevelWithLog(String level) {
        System.out.println("--- ACTUAL DATABASE HIT FOR LEVEL: " + level + " ---");
        return findAllByLevel(level);
    }

    List<WordContent> findAllByLevel(String level);
}
