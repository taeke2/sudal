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
    private Float x;
    private Float y;
    private Float logoWidth;
    private Float signWidth;
    private Float signHeight;
    private int pageIndex;
    private Float rotationDegrees;
    private boolean flipVertical;   // 상하 반전 여부
    private boolean flipHorizontal; // 좌우 반전 여부
    private int columns;    // 열 갯수
    private float rowsDistance;
    private List<PDFImageDto> images;

    @Override
    public String toString() {
        return "PDFDto{" +
                "pdfFilepath='" + pdfFilepath + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", logoWidth=" + logoWidth +
                ", signWidth=" + signWidth +
                ", signHeight=" + signHeight +
                ", pageIndex=" + pageIndex +
                ", rotationDegrees=" + rotationDegrees +
                ", flipVertical=" + flipVertical +
                ", flipHorizontal=" + flipHorizontal +
                ", columns=" + columns +
                ", rowsDistance=" + rowsDistance +
                '}';
    }
}
