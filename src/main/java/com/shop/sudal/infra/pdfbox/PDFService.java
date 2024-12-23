package com.shop.sudal.infra.pdfbox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.util.Matrix;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PDFService {

    public void insertImageInPDF2(PDF2Dto pdfDto) throws IOException {
        ClassPathResource pdfResource = new ClassPathResource(pdfDto.getPdfFilepath());

        List<PDFImage2Dto> images = pdfDto.getImages();
        float x = pdfDto.getX();
        float y = pdfDto.getY();
        float width = pdfDto.getWidth();
        float height = pdfDto.getHeight();
        float radians = (float) Math.toRadians(pdfDto.getRotationDegrees());

        float scaleX = pdfDto.isFlipHorizontal() ? -1 : 1;
        float scaleY = pdfDto.isFlipVertical() ? -1 : 1;


        for (PDFImage2Dto imageDto : images) {
            try (InputStream pdfStream = pdfResource.getInputStream();
                 PDDocument document = PDDocument.load(pdfStream)) {

                PDPage page = document.getPage(pdfDto.getPageIndex());

                PDType0Font font = PDType0Font.load(document, new File("src/main/resources/fonts/NanumGothic.ttf"));
                System.out.println("imageDto = " + imageDto);
                byte[] imageBytes = downloadImageFromURL(imageDto.getImageFilepath());

                PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, imageBytes, "image");

                try (PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true)) {
                    contentStream.saveGraphicsState();

                    Matrix signTransform = new Matrix();
                    signTransform.translate(x, y);
                    signTransform.scale(scaleX, scaleY);
                    signTransform.rotate(radians);

                    contentStream.transform(signTransform);
                    contentStream.drawImage(pdImage, 0, 0, width, height);

                    contentStream.restoreGraphicsState();

                    contentStream.beginText();
                    contentStream.setFont(font, 15); // 폰트 및 크기 설정
                    contentStream.setLeading(14.5f); // 줄 간격 설정
                    contentStream.newLineAtOffset(x - 100, y + 5); // 시작 좌표 (x, y)
                    contentStream.showText(imageDto.getName()); // 텍스트 추가
                    contentStream.endText();
                }
                String s = imageDto.getName().replaceAll(" ", "");
                File outputFile = new File("../" + s + ".pdf"); // 결과 파일 경로 지정
                document.save(outputFile);
            }
        }
    }

    public void insertImageInPDF(PDFDto pdfDto) throws IOException {
        ClassPathResource pdfResource = new ClassPathResource(pdfDto.getPdfFilepath());

        try (InputStream pdfStream = pdfResource.getInputStream();
             PDDocument document = PDDocument.load(pdfStream)) {

            PDPage page = document.getPage(pdfDto.getPageIndex());

            List<PDFImageDto> images = pdfDto.getImages();
            float x = pdfDto.getX();
            float signY = pdfDto.getY();
            float logoY = signY + pdfDto.getSignHeight();
            float signWidth = pdfDto.getSignWidth();
            float signHeight = pdfDto.getSignHeight();
            float logoWidth = pdfDto.getLogoWidth();
            float radians = (float) Math.toRadians(pdfDto.getRotationDegrees());
            int column = pdfDto.getColumns();
            float rowsDistance = pdfDto.getRowsDistance();

            float pdfWidth = getPDFDimensions(page, pdfDto.getPageIndex()).get("width");
            float signWidthDistance = column == 1 ? 0 : (pdfWidth - (2 * x) - (signWidth * column)) / (column - 1);

            float scaleX = pdfDto.isFlipHorizontal() ? -1 : 1;
            float scaleY = pdfDto.isFlipVertical() ? -1 : 1;

            int columnCount = 1;

            for (PDFImageDto imageDto : images) {
                byte[] logoBytes = downloadImageFromURL(imageDto.getLogoFilepath());
                byte[] signBytes = downloadImageFromURL(imageDto.getSignFilepath());

                PDImageXObject pdLogo = PDImageXObject.createFromByteArray(document, logoBytes, "logo");
                PDImageXObject pdSign = PDImageXObject.createFromByteArray(document, signBytes, "sign");

                Map<String, Integer> logoDimensions = getImageDimensions(pdLogo);

                float logoHeight = (logoDimensions.get("height") * pdfDto.getLogoWidth()) / logoDimensions.get("width");

                try (PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true)) {
                    contentStream.saveGraphicsState();

                    Matrix signTransform = new Matrix();
                    signTransform.translate(x, signY);
                    signTransform.scale(scaleX, scaleY);
                    signTransform.rotate(radians);

                    contentStream.transform(signTransform);
                    contentStream.drawImage(pdSign, 0, 0, signWidth, signHeight);

                    contentStream.restoreGraphicsState();
                    contentStream.saveGraphicsState();

                    Matrix logoTransform = new Matrix();
                    logoTransform.translate(x, logoY);
                    logoTransform.scale(scaleX, scaleY);
                    logoTransform.rotate(radians);

                    contentStream.transform(logoTransform);
                    contentStream.drawImage(pdLogo, 0, 0, logoWidth, logoHeight);

                    contentStream.restoreGraphicsState();

                    if (columnCount == column) {
                        x = pdfDto.getX();
                        signY -= (signHeight + logoHeight + rowsDistance);
                        logoY = signY + signHeight;
                        columnCount = 1;
                    } else {
                        x += (signWidth + signWidthDistance);
                        columnCount++;
                    }
                }
            }

            // 수정된 PDF 파일 저장 (덮어쓰기)
            File outputFile = new File("output-" + pdfDto.getPdfFilepath()); // 결과 파일 경로 지정
            document.save(outputFile);
        }
    }

    private Map<String, Float> getPDFDimensions(PDPage page, int pageIndex) {
        PDRectangle mediaBox = page.getMediaBox();

        Map<String, Float> dimensions = new HashMap<>();
        dimensions.put("width", mediaBox.getWidth());
        dimensions.put("height", mediaBox.getHeight());

        return dimensions;
    }

    private Map<String, Integer> getImageDimensions(PDImageXObject image) {
        Map<String, Integer> dimensions = new HashMap<>();
        dimensions.put("width", image.getWidth());
        dimensions.put("height", image.getHeight());
        return dimensions;
    }

    // S3 이미지 URL에서 이미지 데이터를 다운로드하는 메서드
    private byte[] downloadImageFromURL(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000); // 연결 타임아웃 5초
        connection.setReadTimeout(5000);    // 읽기 타임아웃 5초

        try (InputStream inputStream = connection.getInputStream();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            return outputStream.toByteArray();
        } finally {
            connection.disconnect();
        }
    }
}
