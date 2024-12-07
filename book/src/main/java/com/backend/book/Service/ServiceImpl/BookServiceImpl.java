package com.backend.book.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.backend.book.Model.Entity.Book;
import com.backend.book.Repository.BookRepository;
import com.backend.book.Service.BookService;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired 
    BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAllExcludingBookPages();
    }

    public Optional<Book> getBookById(String id) {
        return bookRepository.findById(id);
    }

    public String getBookPage(String id, Long pageNumber){
        Book book=bookRepository.findByBookId(id);
        Map<Long, String> temp=book.getBookPages();
        String a=temp.get(pageNumber);
        return a;
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
    
    public Book updateBook(String id, Book updatedBook) {
        
        Book book=bookRepository.findByBookId(id);
        if (updatedBook.getTitle()!=null) book.setTitle(updatedBook.getTitle());
        if (updatedBook.getAuthor()!=null) book.setAuthor(updatedBook.getAuthor());
        if (updatedBook.getDescription()!=null) book.setDescription(updatedBook.getDescription());
        if (updatedBook.getPublisher()!=null) book.setPublisher(updatedBook.getPublisher());
        return book;
    }

    // public Book updateBook(String id, Book updatedBook) {
    //     return bookRepository.findById(id).map(book -> {
    //         book.setTitle(updatedBook.getTitle());
    //         book.setDescription(updatedBook.getDescription());
    //         book.setAuthor(updatedBook.getAuthor());
    //         book.setPublisher(updatedBook.getPublisher());
    //         // book.setBookCover(updatedBook.getBookCover());
    //         book.setTotalPages(updatedBook.getTotalPages());
    //         book.setPremium(updatedBook.isPremium());
    //         book.setCategories(updatedBook.getCategories());
    //         book.setCloudUrl(updatedBook.getCloudUrl());
    //         return bookRepository.save(book);
    //     }).orElseThrow(() -> new IllegalArgumentException("Book not found with ID: " + id));
    // }

    public String deleteBook(String id) {
        bookRepository.deleteById(id);
        return "Delete successfully";
    }

    public Book updateBookPage(String id, String page, Long pageNumber) {
        Book book = bookRepository.findByBookId(id);
        System.out.println(123132);
        Map<Long, String> bookPages = book.getBookPages();
        bookPages.put(pageNumber, page);
        book.setBookPages(bookPages);
        return bookRepository.save(book); 

    }

    public static final String STORAGE_DIRECTORY = "D:\\1\\1\\DACN\\BE_02\\BE_DACN\\book\\Book_Cover";
    public void updateBookCover(String id, MultipartFile cover) {
        if (cover == null) {
            throw new NullPointerException("fileToSave is null");
        }
        Book book=bookRepository.findByBookId(id);

        var targetFile = new File(STORAGE_DIRECTORY + File.separator + id+".PNG");
        if (!Objects.equals(targetFile.getParent(), STORAGE_DIRECTORY)) {
            throw new SecurityException("Unsupported filename!");
        }
        try{
            Files.copy(cover.getInputStream(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (Exception e){};
        book.setBookCover(targetFile.getPath());
        bookRepository.save(book);
        

    }

    public List<Book> getBookByTittle(String tittle){
        return bookRepository.findByTitleExcludingBookPages(tittle);
    }

}

