package com.test.anu.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="jlpt_word_content")
@Data
public class WordContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String kanji;
    private String furigana;
    private String meaning;
    private String level;

    @Column(columnDefinition = "TEXT")
    private String sentence;
}
