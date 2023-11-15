package org.example;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@RestController
public class TestController {
    private final TestService testService;
    private final ImageRepository imageRepository;

    private static final String UPLOAD_DIR = "./";
    @GetMapping("/test")
    public List<Image>getAllMembers(){
        List<Image> images = testService.getAllImages();
        return images;
    }
    @PostMapping("/image")
    public ResponseEntity<Image> addImage(@RequestParam("imageFile") MultipartFile imageFile){
        // 이미지 파일 처리
        Image savedImage = testService.save(imageFile);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedImage);
    }
    @GetMapping("/images")
    public List<String> getAllImageNames() {
        // 이미지 이름 목록을 데이터베이스에서 불러오기
        List<Image> images = imageRepository.findAll();

        // 이미지 이름 목록만 추출하여 반환
        return images.stream()
                .map(Image::getName)
                .collect(Collectors.toList());
    }

    @GetMapping("/getImage")
    public ResponseEntity<byte[]> getImage(@RequestParam String name) {
        // 이미지 파일 경로를 가져오는 코드 (name을 이용하여)
        // 예시로 이미지 파일을 바이트 배열로 읽어오는 코드 작성
        Path imagePath = Paths.get(UPLOAD_DIR + name);

        try {
            byte[] imageData = Files.readAllBytes(imagePath);
            // 이미지 바이트 배열과 HTTP 상태코드를 반환
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // 이미지 타입에 따라 변경
                    .body(imageData);
        } catch (IOException e) {
            // 파일을 읽을 수 없는 경우 예외 처리
            return ResponseEntity.notFound().build();
        }
    }
}
