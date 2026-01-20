package com.kowihan.acceleo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${app.storage.base-path:/tmp/acceleo-generator}")
    private String basePath;

    @Value("${app.storage.generated-path:}")
    private String generatedPath;

    public FileStorageService() {
    }

    public void initializeStorage() {
        try {
            Path base = Paths.get(basePath);
            Path generated = generatedPath != null && !generatedPath.isEmpty() 
                ? Paths.get(generatedPath) 
                : base.resolve("generated");
            
            Files.createDirectories(base);
            Files.createDirectories(generated);
        } catch (IOException e) {
            throw new RuntimeException("Storage initialization failed", e);
        }
    }

    public Path getBasePath() {
        return Paths.get(basePath);
    }

    public Path getGeneratedPath() {
        if (generatedPath != null && !generatedPath.isEmpty()) {
            return Paths.get(generatedPath);
        }
        return Paths.get(basePath).resolve("generated");
    }

    public Path createProjectDirectory(String projectName) throws IOException {
        String uniqueId = UUID.randomUUID().toString().substring(0, 8);
        String dirName = projectName + "_" + uniqueId;
        Path projectDir = getGeneratedPath().resolve(dirName);
        Files.createDirectories(projectDir);
        return projectDir;
    }

    public Path createTempFile(String prefix, String suffix) throws IOException {
        Path tempDir = getBasePath().resolve("temp");
        Files.createDirectories(tempDir);
        return Files.createTempFile(tempDir, prefix, suffix);
    }

    public void deleteDirectory(Path directory) throws IOException {
        if (Files.exists(directory)) {
            Files.walk(directory)
                .sorted((a, b) -> -a.compareTo(b))
                .forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        // Ignore deletion errors
                    }
                });
        }
    }

    public boolean exists(Path path) {
        return Files.exists(path);
    }
}

