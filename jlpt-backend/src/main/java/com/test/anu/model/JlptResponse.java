package com.test.anu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JlptResponse {

    private List<WordContent> content;
    private PageData page;
}
