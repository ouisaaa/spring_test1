package org.example;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    private String base64ImageData;
    @Builder
    public Image(String name, String base64ImageData){
        this.name=name;
        this.base64ImageData=base64ImageData;
    }
}
