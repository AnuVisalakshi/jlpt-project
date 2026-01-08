package com.test.anu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageData {

    private int size;
    private int number;
    private long totalElements;
    private int totalPages;
}
