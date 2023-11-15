package org.example;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TestService {
    private final ImageRepository imageRepository;
    // 저장할 디렉토리 경로 설정
    private static final String UPLOAD_DIR = "./";

    public List<Image>getAllImages(){
        return imageRepository.findAll();
    }

    public Image save(MultipartFile file) {
        Path filePath = Paths.get(UPLOAD_DIR + file.getOriginalFilename());

        // 파일 저장
        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 저장된 이미지 정보를 생성하여 데이터베이스에 저장
        Image image = new Image();
        image.setName(file.getOriginalFilename());
        // 여기에 다른 이미지 정보 설정

        // 데이터베이스에 저장
        return imageRepository.save(image);
    }
}
