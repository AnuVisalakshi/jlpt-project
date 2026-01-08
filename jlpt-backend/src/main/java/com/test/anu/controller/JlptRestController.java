package com.test.anu.controller;

import com.test.anu.model.JlptResponse;
import com.test.anu.model.WordContent;
import com.test.anu.service.JlptService;
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
    JlptService service;

    @GetMapping("/wordslist/{level}")
    public JlptResponse getWords(
            @PathVariable String level,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return service.getPaginatedWords(level, page, size);
    }

}
