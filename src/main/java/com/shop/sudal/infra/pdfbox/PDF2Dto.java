package com.shop.sudal.infra.pdfbox;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PDF2Dto {
    private String pdfFilepath;
    private Float x;
    private Float y;
    private Float width;
    private Float height;
    private int pageIndex;
    private Float rotationDegrees;
    private boolean flipVertical;   // 상하 반전 여부
    private boolean flipHorizontal; // 좌우 반전 여부
    private int columns;    // 열 갯수
    private float rowsDistance;
    private List<PDFImage2Dto> images;

    @Override
    public String toString() {
        return "PDF2Dto{" +
                "pdfFilepath='" + pdfFilepath + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", pageIndex=" + pageIndex +
                ", rotationDegrees=" + rotationDegrees +
                ", flipVertical=" + flipVertical +
                ", flipHorizontal=" + flipHorizontal +
                ", columns=" + columns +
                ", rowsDistance=" + rowsDistance +
                ", images=" + images +
                '}';
    }
}
