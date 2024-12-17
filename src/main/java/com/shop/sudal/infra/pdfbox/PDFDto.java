package com.shop.sudal.infra.pdfbox;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PDFDto {
    private String pdfFilepath;
    private List<PDFImageDto> images;
}
