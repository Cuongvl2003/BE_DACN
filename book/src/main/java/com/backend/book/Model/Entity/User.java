package com.backend.book.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String userName;
    private String email;
    private String password;
    private LocalDate dob;
    private List<ReadingHistory> readingHistory;

    @Data
    public static class ReadingHistory {
        private String bookId;
        private LocalDate lastRead;
        private int lastPage;
    }
}