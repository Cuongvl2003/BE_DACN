package com.backend.book.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.backend.book.Model.DTO.PageUpdateRequest;
import com.backend.book.Model.Entity.Book;
import com.backend.book.Service.BookService;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/getAllBooks")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/getBookById/{id}")
    public Optional<Book> getBookById(@PathVariable String id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/getBookPage/{id}/pageNumber{pageNumber}")
    public HashMap<String, String> getBookpage(@PathVariable String id, @PathVariable Long pageNumber) {
        return bookService.getBookPage(id, pageNumber);
    }

    @PostMapping("/createBook")
    public Book createBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @PutMapping("/updateBook/{id}")
    public Book updateBook(@PathVariable String id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
        return "Book deleted successfully!";
    }

    // @PutMapping("/123")
    // public Book updateBookPage(@RequestParam String id, @RequestParam MultipartFile page, @RequestParam Long pageNumber) throws TesseractException{
    //     return bookService.updateBookPage2(id, page, pageNumber);
    // }

    @PostMapping("/update-page(NoNeedThisShit)")
    public Book updateBookPage(@RequestBody PageUpdateRequest request) {
        System.out.println(1123);
        return bookService.updateBookPage(request.getId(), request.getPage(), request.getPageNumber());
    }

    @PostMapping(value = "/updateBookCover{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateBookCover(@PathVariable String id, @RequestParam MultipartFile multipartFile) {
        bookService.updateBookCover(id, multipartFile);
    }

    @GetMapping("/getBookByTittle/{tittle}")
    public List<Book> getBookByTittle(@PathVariable String tittle) {
        return bookService.getBookByTittle(tittle);
    }

    @GetMapping("/getBookByAuthor/{author}")
    public List<Book> getBookByAuthor(@PathVariable String author) {
        return bookService.getBookByAuthor(author);
    }

     @GetMapping("/getBookCover")
    public ResponseEntity<Resource> downloadFileFaster(@RequestParam("fileName") String filename) {
        //log.log(Level.INFO, "[FASTER] Download with /download-faster");
        try {
            var fileToDownload = bookService.getDownloadFile(filename);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                    .contentLength(fileToDownload.length())
                    .contentType(MediaType.IMAGE_PNG)
                    .body(new FileSystemResource(fileToDownload));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

