package com.shop.sudal.infra.pdfbox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
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
import java.util.List;

@Service
public class PDFService {

    public void insertImageInPDF(PDFDto pdfDto) throws IOException {
        // 리소스 폴더에서 PDF 파일과 이미지 파일 읽기
        ClassPathResource pdfResource = new ClassPathResource(pdfDto.getPdfFilepath());
//        ClassPathResource imageResource = new ClassPathResource(pdfDto.getImageFileName());

        try (InputStream pdfStream = pdfResource.getInputStream();
             PDDocument document = PDDocument.load(pdfStream)) {

            List<PDFImageDto> images = pdfDto.getImages();

            for (PDFImageDto imageDto : images) {
                // 삽입할 페이지 선택
                PDPage page = document.getPage(imageDto.getPageIndex());

                // 이미지 URL에서 이미지 데이터 가져오기
                byte[] imageBytes = downloadImageFromURL(imageDto.getImageFilepath());

                // 이미지 삽입
                PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, imageBytes, "image");

                float radians = (float) Math.toRadians(imageDto.getRotationDegrees());

                // 콘텐츠 스트림 시작
                try (PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true)) {
                    // 변환 매트릭스 설정
                    contentStream.saveGraphicsState(); // 현재 그래픽 상태 저장

                    // 이미지가 상하 반전되지 않도록 변환 매트릭스 설정
                    Matrix transform = new Matrix();
                    transform.translate(imageDto.getX(), imageDto.getY() + imageDto.getHeight());
                    transform.scale(1, -1);
                    transform.rotate(radians);

                    contentStream.transform(transform);

                    // 이미지 삽입
                    contentStream.drawImage(pdImage, 0, 0, imageDto.getWidth(), imageDto.getHeight());

                    contentStream.restoreGraphicsState(); // 이전 그래픽 상태로 복원
                }
            }

            // 수정된 PDF 파일 저장 (덮어쓰기)
            File outputFile = new File("output-" + pdfDto.getPdfFilepath()); // 결과 파일 경로 지정
            document.save(outputFile);
        }
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
