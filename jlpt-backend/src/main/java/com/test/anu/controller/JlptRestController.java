package com.test.anu.controller;

import com.test.anu.model.WordContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import com.test.anu.repository.JlptRepository;

@RestController
@RequestMapping("/api/jlpt")
@CrossOrigin(origins = "http://localhost:5173")
public class JlptRestController {

    @Autowired
    JlptRepository repository;

    @GetMapping("/wordslist/{level}")
    public Page<WordContent> getWords(
            @PathVariable String level,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return repository.findByLevel(level.toUpperCase(), PageRequest.of(page, size));
    }

}
