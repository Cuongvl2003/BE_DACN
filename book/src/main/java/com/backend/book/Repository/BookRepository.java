package com.backend.book.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.backend.book.Model.Entity.Book;


@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    Book findByBookId(String bookId);
}
