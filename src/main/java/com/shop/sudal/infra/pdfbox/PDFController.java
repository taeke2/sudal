package com.shop.sudal.infra.pdfbox;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/pdfs")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PDFController {

    private final PDFService pdfService;

    @PostMapping("/add-images")
    public ResponseEntity<String> addImageToPDF(@RequestBody PDFDto pdfDto) {
        try {
            // 이미지 삽입
            pdfService.insertImageInPDF(pdfDto);

            return ResponseEntity.ok("Image added successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add image");
        }
    }
}
