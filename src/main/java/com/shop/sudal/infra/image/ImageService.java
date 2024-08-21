package com.shop.sudal.infra.image;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageService {

    public void removeBackground(ImageDto imageDto) throws IOException {
        // 리소스 폴더에서 입력 이미지 파일 로드
        ClassPathResource imageResource = new ClassPathResource(imageDto.getInputFilePath());

        // 이미지 파일을 InputStream으로 읽기
        try (InputStream imageStream = imageResource.getInputStream()) {
            BufferedImage image = ImageIO.read(imageStream);

            // 투명 배경이 있는 새 이미지 생성
            BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

            // 이미지에 대한 그래픽스 컨텍스트 가져오기
            Graphics2D g = newImage.createGraphics();

            // 모든 픽셀을 반복하면서 배경색을 투명하게 설정
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    int pixel = image.getRGB(x, y);
                    Color color = new Color(pixel, true);

                    // 배경색과 일치하는 경우 해당 픽셀을 투명하게 설정
                    if (color.equals(imageDto.getColor())) {
                        newImage.setRGB(x, y, 0x00FFFFFF); // 완전히 투명한 픽셀 설정
                    } else {
                        newImage.setRGB(x, y, pixel); // 원래 픽셀 유지
                    }
                }
            }

            g.dispose();

            // 결과 이미지를 프로젝트 루트 디렉토리에 저장 (PNG 형식)
            File outputFile = new File(imageDto.getOutputFilePath());
            ImageIO.write(newImage, "png", outputFile);
        }
    }
}
