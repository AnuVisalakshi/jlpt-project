package com.test.anu.repository;

import com.test.anu.model.WordContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JlptRepository extends JpaRepository<WordContent, Long> {

    Page<WordContent> findByLevel(String level, Pageable pageable);
}
