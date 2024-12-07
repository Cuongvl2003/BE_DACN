package com.backend.book.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.backend.book.Model.Entity.Book;

public interface BookService {
    public List<Book> getAllBooks();
    public Optional<Book> getBookById(String id);

    public String getBookPage(String id, Long pageNumber);
    public Book saveBook(Book book);
    public Book updateBook(String id, Book book);
    public String deleteBook(String id);
    public Book updateBookPage(String id, String page, Long pageNumber);
   // public Book updateBookPage2(String id, MultipartFile page, Long pageNumber);
    public void updateBookCover(String id, MultipartFile cover);
    public List<Book> getBookByTittle(String tittle);
}
