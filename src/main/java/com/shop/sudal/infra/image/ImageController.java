package com.shop.sudal.infra.image;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/remove-backgrounds")
    public ResponseEntity<String> removeBackgroundImage(@RequestBody ImageDto imageDto) {
        try {
            imageService.removeBackground(imageDto);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Image background removed successfully");
    }
}
