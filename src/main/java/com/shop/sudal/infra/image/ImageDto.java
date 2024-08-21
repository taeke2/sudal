package com.shop.sudal.infra.image;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.awt.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {
    private String inputFilePath;
    private String outputFilePath;
    private Color color;
}
