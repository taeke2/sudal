package com.shop.sudal.infra.pdfbox;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PDFImageDto {
    private String logoFilepath;
    private String signFilepath;

    @Override
    public String toString() {
        return "PDFImageDto{" +
                "logoFilepath='" + logoFilepath + '\'' +
                ", signFilepath='" + signFilepath + '\'' +
                '}';
    }
}
