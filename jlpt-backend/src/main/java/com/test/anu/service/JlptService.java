package com.test.anu.service;

import com.test.anu.model.JlptResponse;
import com.test.anu.model.PageData;
import com.test.anu.model.WordContent;
import com.test.anu.repository.JlptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JlptService {

    @Autowired
    JlptRepository repository;

    @Cacheable(value = "jlptCache", key = "#level")
    public List<WordContent> getFullListByLevel(String level) {
        System.out.println("--- CACHE MISS: Fetching from DB for level " + level + " ---");
        return repository.findAllByLevel(level);
    }

    public JlptResponse getPaginatedWords(String level, int page, int size) {
        List<WordContent> allWords = getFullListByLevel(level.toUpperCase());

        int start = Math.min(page * size, allWords.size());
        int end = Math.min(start + size, allWords.size());
        List<WordContent> paginatedList = allWords.subList(start, end);

        int totalPages = (int) Math.ceil((double) allWords.size() / size);

        PageData pageData = new PageData(size, page, allWords.size(), totalPages);
        return new JlptResponse(paginatedList, pageData);
    }
}
