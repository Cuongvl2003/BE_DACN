package com.backend.book.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.book.Model.Entity.Book;


@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    Book findByBookId(String bookId);

    @Query(value = "{}", fields = "{ 'bookPages': 0 }")
    List<Book> findAllExcludingBookPages();
}
