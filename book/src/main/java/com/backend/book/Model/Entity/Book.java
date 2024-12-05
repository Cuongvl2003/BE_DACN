package com.backend.book.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "books")
public class Book {
    @Id
    private String bookId;
    private String title;
    private String description;
    private String author;
    private String publisher;
    private String bookCover;
    private int totalPages;
    private boolean isPremium;
    private List<String> categories;
    private String cloudUrl;
    private Map<Long, String> bookPages;
}

