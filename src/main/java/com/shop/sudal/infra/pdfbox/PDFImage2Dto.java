package com.shop.sudal.infra.pdfbox;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PDFImage2Dto {
    String name;
    String imageFilepath;

    @Override
    public String toString() {
        return "PDFImage2Dto{" +
                "name='" + name + '\'' +
                ", imageFilepath='" + imageFilepath + '\'' +
                '}';
    }
}
