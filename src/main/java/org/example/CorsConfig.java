package org.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/image")
                .allowedOrigins("http://localhost:63342") // 허용할 origin 설정
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 사용할 HTTP 메서드 지정
                .allowedHeaders("*") // 모든 헤더 허용
                .allowCredentials(false) // 인증 정보 허용
                .maxAge(3600); // preflight 요청의 유효 시간 설정
        registry.addMapping("/images")
                .allowedOrigins("http://localhost:63342") // 허용할 origin 설정
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 사용할 HTTP 메서드 지정
                .allowedHeaders("*") // 모든 헤더 허용
                .allowCredentials(false) // 인증 정보 허용
                .maxAge(3600); // preflight 요청의 유효 시간 설정
        registry.addMapping("/getImage")
                .allowedOrigins("http://localhost:63342") // 허용할 origin 설정
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 사용할 HTTP 메서드 지정
                .allowedHeaders("*") // 모든 헤더 허용
                .allowCredentials(false) // 인증 정보 허용
                .maxAge(3600); // preflight 요청의 유효 시간 설정

    }
}
