package com.kowihan.acceleo.controller;

import com.kowihan.acceleo.model.dto.GenerationRequest;
import com.kowihan.acceleo.model.dto.GenerationResponse;
import com.kowihan.acceleo.model.dto.ProjectSpecification;
import com.kowihan.acceleo.model.metamodel.ApplicationModel;
import com.kowihan.acceleo.service.AcceleoRunnerService;
import com.kowihan.acceleo.service.FileStorageService;
import com.kowihan.acceleo.service.ModelGenerationService;
import com.kowihan.acceleo.service.ZipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/generation")
@CrossOrigin(origins = "*")
public class GenerationController {

    @Autowired
    private ModelGenerationService modelGenerationService;

    @Autowired
    private AcceleoRunnerService acceleoRunnerService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ZipService zipService;

    @PostMapping("/generate-from-spec")
    public ResponseEntity<GenerationResponse> generateFromSpecification(
            @Valid @RequestBody ProjectSpecification specification) {
        try {
            // Initialize storage
            fileStorageService.initializeStorage();

            // Convert ProjectSpecification to ApplicationModel
            ApplicationModel model = convertToApplicationModel(specification);

            // Generate XMI from specification
            Path xmiPath = modelGenerationService.generateXMIFromSpecification(model);

            // Create project directory
            Path projectDir = fileStorageService.createProjectDirectory(model.getProjectName());

            // Execute Acceleo
            Path generatedProjectPath = acceleoRunnerService.executeAcceleo(xmiPath, projectDir);

            // Create ZIP archive
            Path zipPath = zipService.createZipArchive(generatedProjectPath, model.getProjectName());
            long fileSize = zipService.getFileSize(zipPath);

            // Build response
            GenerationResponse response = GenerationResponse.builder()
                    .success(true)
                    .message("Project generated successfully")
                    .projectName(model.getProjectName())
                    .zipFilePath(zipPath.toString())
                    .downloadUrl("/api/generation/download/" + zipPath.getFileName().toString())
                    .fileSize(fileSize)
                    .generatedAt(LocalDateTime.now())
                    .build();

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            GenerationResponse errorResponse = GenerationResponse.builder()
                    .success(false)
                    .message("Generation failed: " + e.getMessage())
                    .error(e.getMessage())
                    .generatedAt(LocalDateTime.now())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/generate-from-xmi")
    public ResponseEntity<GenerationResponse> generateFromXMI(
            @RequestParam("xmi_file") MultipartFile xmiFile,
            @RequestParam(value = "projectName", required = false) String projectName) {
        try {
            fileStorageService.initializeStorage();

            // Save uploaded XMI file
            Path tempXmiPath = fileStorageService.createTempFile("uploaded_", ".xmi");
            xmiFile.transferTo(tempXmiPath.toFile());

            String finalProjectName = projectName != null ? projectName : "GeneratedProject";

            // Create project directory
            Path projectDir = fileStorageService.createProjectDirectory(finalProjectName);

            // Execute Acceleo
            Path generatedProjectPath = acceleoRunnerService.executeAcceleo(tempXmiPath, projectDir);

            // Create ZIP archive
            Path zipPath = zipService.createZipArchive(generatedProjectPath, finalProjectName);
            long fileSize = zipService.getFileSize(zipPath);

            GenerationResponse response = GenerationResponse.builder()
                    .success(true)
                    .message("Project generated from XMI successfully")
                    .projectName(finalProjectName)
                    .zipFilePath(zipPath.toString())
                    .downloadUrl("/api/generation/download/" + zipPath.getFileName().toString())
                    .fileSize(fileSize)
                    .generatedAt(LocalDateTime.now())
                    .build();

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            GenerationResponse errorResponse = GenerationResponse.builder()
                    .success(false)
                    .message("Generation failed: " + e.getMessage())
                    .error(e.getMessage())
                    .generatedAt(LocalDateTime.now())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadZip(@PathVariable String fileName) {
        try {
            Path generatedPath = fileStorageService.getGeneratedPath();
            
            // Chercher récursivement dans le répertoire generated
            Path zipPath = null;
            try {
                java.util.stream.Stream<Path> files = Files.walk(generatedPath);
                java.util.Optional<Path> found = files
                    .filter(path -> {
                        String name = path.getFileName().toString();
                        return name.equals(fileName) || name.equals(fileName + ".zip");
                    })
                    .filter(Files::isRegularFile)
                    .findFirst();
                files.close();
                if (found.isPresent()) {
                    zipPath = found.get();
                }
            } catch (IOException e) {
                System.err.println("Error searching for ZIP: " + e.getMessage());
            }
            
            // Si pas trouvé, essayer directement dans generated
            if (zipPath == null || !Files.exists(zipPath)) {
                zipPath = generatedPath.resolve(fileName);
                if (!Files.exists(zipPath) && !fileName.endsWith(".zip")) {
                    zipPath = generatedPath.resolve(fileName + ".zip");
                }
            }
            
            if (zipPath == null || !Files.exists(zipPath)) {
                System.err.println("ZIP not found: " + fileName);
                System.err.println("Searched in: " + generatedPath);
                return ResponseEntity.notFound().build();
            }

            System.out.println("Found ZIP at: " + zipPath);
            Resource resource = new FileSystemResource(zipPath.toFile());
            
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);

        } catch (Exception e) {
            System.err.println("Error downloading ZIP: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/health")
    public ResponseEntity<Object> health() {
        return ResponseEntity.ok().body(new Object() {
            public final String status = "UP";
            public final String service = "Spring Boot Acceleo Generator";
            public final String timestamp = LocalDateTime.now().toString();
        });
    }

    @GetMapping("/frameworks")
    public ResponseEntity<List<String>> getFrameworks() {
        return ResponseEntity.ok(Arrays.asList("DJANGO", "FLASK"));
    }

    private ApplicationModel convertToApplicationModel(ProjectSpecification spec) {
        ApplicationModel model = new ApplicationModel();
        model.setProjectName(spec.getProjectName());
        model.setFramework(spec.getFramework());
        model.setPythonVersion(spec.getPythonVersion());
        model.setDescription(spec.getDescription());
        model.setDatabase(spec.getDatabase());
        model.setAuthentication(spec.getAuthentication());
        model.setApiFeatures(spec.getApiFeatures());
        model.setEntities(spec.getEntities());
        return model;
    }
}

