package com.backend.book.Controller;

import net.sourceforge.tess4j.TesseractException;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.book.Model.Entity.Book;
import com.backend.book.Service.BookService;
import com.backend.book.Service.OcrService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;;



@RestController
@RequestMapping("/ocr")
public class OcrController {

    private final OcrService ocrService;
    @Autowired BookService bookService;

    public OcrController(OcrService ocrService) {
        this.ocrService = ocrService;
    }
    @PostMapping(value = "/addImage(testOCR)", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> getImageToString(@RequestParam("file") MultipartFile multipartFile) throws TesseractException {
        return new ResponseEntity<>(ocrService.getImageString(multipartFile), HttpStatus.OK);
    }

    // @GetMapping("/123")
    // public String asd(@RequestParam MultipartFile multipartFile)  {
    //     return "123";
    // }

    // @PostMapping(value = "/upload-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    // public String uploadFile(@RequestParam("file") MultipartFile file) {return "123123";}

    // @PostMapping(value = "/updateBookPage{page}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    // public Book updateBookPage(@RequestParam Book book, @RequestParam List<MultipartFile> page) throws TesseractException{
    //    // System.out.println("Request Content-Type: " + request.getContentType());
    //    System.out.println("aaaa");
    //     Book book1 =bookService.saveBook(book);
    //     System.out.println("aaaa");
    //     String id=book1.getBookId();
    //     return ocrService.updateBookPage(id, page);
    // }
    // @PostMapping(value = "/updateBookPage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    // public Book updateBookPage(
    //     @RequestPart("book") Book book, 
    //     @RequestPart(value = "files", required = false) List<MultipartFile> files,
    //     HttpServletRequest request) throws TesseractException {
    //         System.out.println("Request Content-Type: " + request.getContentType());
    //         System.out.println("Received Book: " + book);
    //         if (files != null && !files.isEmpty()) {
    //         files.forEach(file -> System.out.println("Received File: " + file.getOriginalFilename()));
    //         }
    //         return bookService.saveBook(book);
    //     }

    @PostMapping(value = "/createBook", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Book createBook(@RequestParam String title,
                                @RequestParam String description,
                                @RequestParam String author,
                                @RequestParam String publisher,
                                @RequestParam(required = false) String bookCover,
                                @RequestParam(required = false) Long totalPages,
                                @RequestParam(required = false) boolean isPremium,
                                @RequestParam List<String> categories,
                                @RequestParam(required = false) String cloudUrl,
                                @RequestParam MultipartFile bookCoverReal,
                                @RequestParam List<MultipartFile> page) throws TesseractException{
       // System.out.println("Request Content-Type: " + request.getContentType());
       Book book = Book.builder()
                .title(title)
                .description(description)
                .author(author)
                .publisher(publisher)
                .bookCover("bookCover")
                .totalPages(totalPages)
                .isPremium(false)
                .categories(categories)
                .cloudUrl("cloudUrl")
                .build();
        Book book2=bookService.saveBook(book);
        String id=book2.getBookId();
        bookService.updateBookCover(id, bookCoverReal);
        return ocrService.createBook(id, page);
    }   
    
    @PostMapping(value = "/updateBookPage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Book updateBookPage(@RequestParam String id,
                                @RequestParam List<MultipartFile> page) throws TesseractException{
       // System.out.println("Request Content-Type: " + request.getContentType());
        return ocrService.updateBookPage(id, page);
    }
}
