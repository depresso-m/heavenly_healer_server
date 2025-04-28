package com.pharmacy.heavenly_healer_server;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class FileStorageConfig {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostConstruct
    public void init() {
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs(); // Создаем папку, если она не существует
        }
    }

    @Bean
    public String uploadDir() {
        return uploadDir;
    }
}
