package com.kowihan.acceleo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class AcceleoRunnerService {

    private final ResourceLoader resourceLoader;
    private final FileStorageService fileStorageService;

    @Value("${acceleo.template.main:main.mtl}")
    private String templateFileName;

    @Value("${acceleo.metamodel.path:metamodels/APIMetamodel.ecore}")
    private String metamodelPath;

    @Value("${acceleo.python.script:python acceleo_runner.py}")
    private String pythonCommand;

    public AcceleoRunnerService(ResourceLoader resourceLoader, FileStorageService fileStorageService) {
        this.resourceLoader = resourceLoader;
        this.fileStorageService = fileStorageService;
    }

    public Path executeAcceleo(Path xmiModelPath, Path outputPath) throws Exception {
        // Get template file
        Resource templateResource = resourceLoader.getResource("classpath:templates/acceleo/" + templateFileName);
        Path templatePath = extractResourceToTemp(templateResource, "template_", ".mtl");

        // Get metamodel file
        Resource metamodelResource = resourceLoader.getResource("classpath:" + metamodelPath);
        Path metamodelFilePath = extractResourceToTemp(metamodelResource, "metamodel_", ".ecore");

        // Create output directory
        Files.createDirectories(outputPath);

        try {
            // Try to execute via Python wrapper or Acceleo script
            return executeViaScript(templatePath, metamodelFilePath, xmiModelPath, outputPath);
        } catch (Exception e) {
            // Fallback: Create a basic project structure
            return createFallbackStructure(outputPath, xmiModelPath);
        }
    }

    private Path executeViaScript(Path templatePath, Path metamodelPath, Path xmiModelPath, Path outputPath) throws Exception {
        // Get the script path from configuration
        Path scriptPath = getScriptPath();
        
        String scriptPathStr = scriptPath.toString();
        boolean isShellScript = scriptPathStr.endsWith(".sh");

        // Build command based on script type
        List<String> command = new ArrayList<>();
        
        if (isShellScript) {
            // For shell scripts (.sh), execute directly
            System.out.println("🚀 Executing Acceleo via shell script: " + scriptPathStr);
            command.add(scriptPathStr);
        } else {
            // For Python scripts (.py), use python3
            System.out.println("🐍 Executing Acceleo via Python: " + scriptPathStr);
            
            // Check if Python is available
            ProcessBuilder checkPython = new ProcessBuilder("python3", "--version");
            Process checkProcess = checkPython.start();
            int checkExitCode = checkProcess.waitFor();
            
            if (checkExitCode != 0) {
                throw new RuntimeException("Python3 not found. Please install Python 3.");
            }
            
            command.add("python3");
            command.add(scriptPathStr);
        }
        
        // Add common arguments
        command.add("--template");
        command.add(templatePath.toString());
        command.add("--metamodel");
        command.add(metamodelPath.toString());
        command.add("--model");
        command.add(xmiModelPath.toString());
        command.add("--output");
        command.add(outputPath.toString());

        System.out.println("📝 Executing command: " + String.join(" ", command));

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        // Read output
        StringBuilder output = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("  " + line);
                output.append(line).append("\n");
            }
        }

        int exitCode = process.waitFor();
        
        if (exitCode != 0) {
            throw new RuntimeException("Acceleo execution failed with exit code " + exitCode + ": " + output.toString());
        }

        System.out.println("✅ Acceleo execution completed successfully");
        return outputPath;
    }

    private Path getScriptPath() throws IOException {
        // Use the configured pythonCommand value (can be .sh or .py)
        String configuredPath = pythonCommand;
        
        System.out.println("🔍 Looking for script: " + configuredPath);
        
        // Remove "python3" prefix if present (legacy config)
        if (configuredPath.startsWith("python3 ")) {
            configuredPath = configuredPath.substring(8).trim();
        }
        if (configuredPath.startsWith("python ")) {
            configuredPath = configuredPath.substring(7).trim();
        }
        
        // Check if it's an absolute path
        Path scriptPath = Path.of(configuredPath);
        if (scriptPath.isAbsolute()) {
            if (Files.exists(scriptPath)) {
                System.out.println("✅ Found script at configured absolute path: " + scriptPath);
                return scriptPath;
            } else {
                throw new IOException("Script not found at configured absolute path: " + scriptPath);
            }
        }
        
        // Try relative to project root
        Path currentDir = Path.of("").toAbsolutePath();
        Path relativeScript = currentDir.resolve(configuredPath).normalize();
        if (Files.exists(relativeScript)) {
            System.out.println("✅ Found script relative to project: " + relativeScript);
            return relativeScript;
        }
        
        // If nothing found, throw error - do NOT fallback to searching for acceleo_runner.py
        throw new IOException("Script not found at configured path: '" + configuredPath + "'" + 
            "\n  Absolute path checked: " + scriptPath + 
            "\n  Relative path checked: " + relativeScript + 
            "\n  Current directory: " + currentDir +
            "\n  Please verify acceleo.python.script in application.properties points to an existing file.");
    }

    private Path extractResourceToTemp(Resource resource, String prefix, String suffix) throws IOException {
        Path tempFile = fileStorageService.createTempFile(prefix, suffix);
        Files.copy(resource.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);
        return tempFile;
    }

    private Path createFallbackStructure(Path outputPath, Path xmiModelPath) throws IOException {
        Files.createDirectories(outputPath);
        
        // Create a basic README
        Path readmePath = outputPath.resolve("README.md");
        Files.writeString(readmePath, 
            "# Generated Project\n\n" +
            "This project was generated using Kowihan API Generator.\n\n" +
            "**Note:** Full Acceleo integration requires the configured script.\n\n" +
            "To enable full generation:\n" +
            "1. Ensure the script (Python or Shell) is available\n" +
            "2. Configure acceleo.python.script in application.properties\n" +
            "3. For shell scripts: chmod +x script.sh\n\n" +
            "XMI Model used: " + xmiModelPath.getFileName() + "\n"
        );
        
        return outputPath;
    }
}