package com.backend.book.Controller;

import net.sourceforge.tess4j.TesseractException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.book.Model.Entity.Book;
import com.backend.book.Service.OcrService;

import org.springframework.web.bind.annotation.PostMapping;;



@RestController
@RequestMapping("/ocr")
public class OcrController {

    private final OcrService ocrService;

    public OcrController(OcrService ocrService) {
        this.ocrService = ocrService;
    }
    @PostMapping(value = "/addImage(testOCR)", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> getImageToString(@RequestParam MultipartFile multipartFile) throws TesseractException {
        return new ResponseEntity<>(ocrService.getImageString(multipartFile), HttpStatus.OK);
    }

    // @GetMapping("/123")
    // public String asd(@RequestParam MultipartFile multipartFile)  {
    //     return "123";
    // }

    // @PostMapping(value = "/upload-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    // public String uploadFile(@RequestParam("file") MultipartFile file) {return "123123";}

    @PostMapping(value = "/updateBookPage/{id}/pageNumber/{pageNumber}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Book updateBookPage(@RequestParam String id, @RequestParam MultipartFile page, @RequestParam Long pageNumber) throws TesseractException{
        return ocrService.updateBookPage(id, page, pageNumber);
    }
}
