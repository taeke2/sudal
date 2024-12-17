package com.shop.sudal.infra.pdfbox;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PDFImageDto {
    private String imageFilepath;
    private Float x;
    private Float y;
    private Float width;
    private Float height;
    private int pageIndex;
    private Float rotationDegrees;
}
