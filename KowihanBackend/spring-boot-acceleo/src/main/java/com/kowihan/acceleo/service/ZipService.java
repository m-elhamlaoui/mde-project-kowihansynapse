package com.kowihan.acceleo.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ZipService {

    public Path createZipArchive(Path sourceDirectory, String zipFileName) throws IOException {
        // Créer le ZIP dans le répertoire parent (generated) pour faciliter l'accès
        // Le ZIP contient le répertoire du projet, donc on le place à côté
        Path zipPath = sourceDirectory.getParent().resolve(zipFileName + ".zip");
        
        System.out.println("Creating ZIP archive:");
        System.out.println("  Source directory: " + sourceDirectory);
        System.out.println("  ZIP path: " + zipPath);
        
        try (FileOutputStream fos = new FileOutputStream(zipPath.toFile());
             ZipOutputStream zos = new ZipOutputStream(fos)) {
            
            zipDirectory(sourceDirectory.toFile(), sourceDirectory.toFile().getName(), zos);
        }
        
        long zipSize = Files.size(zipPath);
        System.out.println("  ZIP created successfully, size: " + zipSize + " bytes");
        
        return zipPath;
    }

    private void zipDirectory(File sourceFile, String entryName, ZipOutputStream zos) throws IOException {
        if (sourceFile.isDirectory()) {
            File[] files = sourceFile.listFiles();
            if (files != null) {
                for (File file : files) {
                    String newEntryName = entryName + "/" + file.getName();
                    zipDirectory(file, newEntryName, zos);
                }
            }
        } else {
            try (FileInputStream fis = new FileInputStream(sourceFile)) {
                ZipEntry zipEntry = new ZipEntry(entryName);
                zos.putNextEntry(zipEntry);
                
                byte[] buffer = new byte[1024];
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
                
                zos.closeEntry();
            }
        }
    }

    public long getFileSize(Path filePath) throws IOException {
        return Files.size(filePath);
    }
}

