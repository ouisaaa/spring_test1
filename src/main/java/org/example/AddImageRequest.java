package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddImageRequest {
    private String name;
    private String base64ImageData;
    public Image toEntity(){
        return Image.builder()
                .name(name)
                .base64ImageData(base64ImageData)
                .build();
    }
}
