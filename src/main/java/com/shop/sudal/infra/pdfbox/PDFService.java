package com.shop.sudal.infra.pdfbox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.util.Matrix;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
public class PDFService {

    public void insertImageInPDF(PDFDto pdfDto) throws IOException {
        // 리소스 폴더에서 PDF 파일과 이미지 파일 읽기
        ClassPathResource pdfResource = new ClassPathResource(pdfDto.getPdfFileName());
        ClassPathResource imageResource = new ClassPathResource(pdfDto.getImageFileName());

        try (InputStream pdfStream = pdfResource.getInputStream(); PDDocument document = PDDocument.load(pdfStream)) {
            // 삽입할 페이지 선택
            PDPage page = document.getPage(pdfDto.getPageIndex());

            // 이미지 로드
            try (InputStream imageStream = imageResource.getInputStream()) {
                PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, imageStream.readAllBytes(), pdfDto.getImageFileName());

                // 회전 각도를 라디안으로 변환
                float radians = (float) Math.toRadians(pdfDto.getRotationDegrees());

                // 콘텐츠 스트림 시작
                try (PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true)) {
                    // 변환 매트릭스 설정
                    contentStream.saveGraphicsState(); // 현재 그래픽 상태 저장

                    // 이미지가 상하 반전되지 않도록 변환 매트릭스 설정
                    Matrix transform = new Matrix();
                    transform.translate(pdfDto.getX(), pdfDto.getY() + pdfDto.getHeight());
                    transform.scale(1, -1);
                    transform.rotate(radians);

                    contentStream.transform(transform);

                    // 이미지 삽입
                    contentStream.drawImage(pdImage, 0, 0, pdfDto.getWidth(), pdfDto.getHeight());

                    contentStream.restoreGraphicsState(); // 이전 그래픽 상태로 복원
                }
            }

            // 수정된 PDF 파일 저장 (덮어쓰기)
            File outputFile = new File("output-" + pdfDto.getPdfFileName()); // 결과 파일 경로 지정
            document.save(outputFile);
        }
    }

}
