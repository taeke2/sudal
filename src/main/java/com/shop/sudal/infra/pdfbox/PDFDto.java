package com.shop.sudal.infra.pdfbox;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PDFDto {
    private String pdfFileName;
    private String imageFileName;
    private float x;
    private float y;
    private float width;
    private float height;
    private int pageIndex;
    private float rotationDegrees;
}
