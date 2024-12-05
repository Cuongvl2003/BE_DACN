package com.backend.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import net.sourceforge.tess4j.Tesseract;

@SpringBootApplication
public class BookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}

	@Bean
    public Tesseract tesseract() {
        Tesseract tesseract = new Tesseract();
        tesseract.setLanguage("eng");
        tesseract.setDatapath("D:\\1\\1\\DACN\\BE_02\\book\\tess\\tessdata");
        return tesseract;
    }

}
