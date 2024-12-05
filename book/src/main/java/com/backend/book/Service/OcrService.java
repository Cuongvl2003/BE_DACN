package com.backend.book.Service;


import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.backend.book.Model.Entity.Book;
import com.backend.book.Repository.BookRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.Objects;

@Service
public class OcrService {
    private final Tesseract tesseract;

    public static final String STORAGE_DIRECTORY = "D:\\1\\1\\DACN\\BE_02\\book\\Book_Page";

    public void saveFile(MultipartFile fileToSave) throws IOException {
        if (fileToSave == null) {
            throw new NullPointerException("fileToSave is null");
        }
        var targetFile = new File(STORAGE_DIRECTORY + File.separator + fileToSave.getOriginalFilename());
        if (!Objects.equals(targetFile.getParent(), STORAGE_DIRECTORY)) {
            throw new SecurityException("Unsupported filename!");
        }
        Files.copy(fileToSave.getInputStream(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    public OcrService(Tesseract tesseract) {
        this.tesseract = tesseract;
    }

    public static final String BASEURL="D:\\1\\1\\DACN\\BE_02\\book\\Book_Page";

    public String getImageString(MultipartFile multipartFile) throws TesseractException {
        System.out.println(multipartFile.getOriginalFilename());
        final String orginalFileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        System.out.println("2222");
        Path filePath = Paths.get(BASEURL+"\\"+orginalFileName);
        System.out.println(filePath);
        try{
            this.saveFile(multipartFile);
        }
        catch (Exception e){};
        final String orcToString = tesseract.doOCR(new File(String.valueOf(filePath)));
        System.out.println(orcToString);
        return orcToString;
    }

    @Autowired BookRepository bookRepository;

    public Book updateBookPage(String id, MultipartFile multipartFile, Long pageNumber) throws TesseractException {
        Book book = bookRepository.findByBookId(id);
        Map<Long, String> bookPages = book.getBookPages();

        System.out.println(multipartFile.getOriginalFilename());
        final String orginalFileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        System.out.println("2222");
        Path filePath = Paths.get(BASEURL+"\\"+orginalFileName);
        System.out.println(filePath);
        try{
            this.saveFile(multipartFile);
        }
        catch (Exception e){};
        final String orcToString = tesseract.doOCR(new File(String.valueOf(filePath)));
        System.out.println(orcToString);

        bookPages.put(pageNumber, orcToString);
        book.setBookPages(bookPages);
        return bookRepository.save(book);
    }

}
