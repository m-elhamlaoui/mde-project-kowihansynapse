package com.kowihan.acceleo.model.dto;

import java.time.LocalDateTime;

public class GenerationResponse {
    private Boolean success;
    private String message;
    private String projectName;
    private String zipFilePath;
    private String downloadUrl;
    private Long fileSize;
    private LocalDateTime generatedAt;
    private String error;

    // Constructeur par défaut
    public GenerationResponse() {
    }

    // Constructeur avec builder
    private GenerationResponse(Builder builder) {
        this.success = builder.success;
        this.message = builder.message;
        this.projectName = builder.projectName;
        this.zipFilePath = builder.zipFilePath;
        this.downloadUrl = builder.downloadUrl;
        this.fileSize = builder.fileSize;
        this.generatedAt = builder.generatedAt;
        this.error = builder.error;
    }

    // Getters et Setters
    public Boolean getSuccess() { 
        return success; 
    }
    
    public void setSuccess(Boolean success) { 
        this.success = success; 
    }
    
    public String getMessage() { 
        return message; 
    }
    
    public void setMessage(String message) { 
        this.message = message; 
    }
    
    public String getProjectName() { 
        return projectName; 
    }
    
    public void setProjectName(String projectName) { 
        this.projectName = projectName; 
    }
    
    public String getZipFilePath() { 
        return zipFilePath; 
    }
    
    public void setZipFilePath(String zipFilePath) { 
        this.zipFilePath = zipFilePath; 
    }
    
    public String getDownloadUrl() { 
        return downloadUrl; 
    }
    
    public void setDownloadUrl(String downloadUrl) { 
        this.downloadUrl = downloadUrl; 
    }
    
    public Long getFileSize() { 
        return fileSize; 
    }
    
    public void setFileSize(Long fileSize) { 
        this.fileSize = fileSize; 
    }
    
    public LocalDateTime getGeneratedAt() { 
        return generatedAt; 
    }
    
    public void setGeneratedAt(LocalDateTime generatedAt) { 
        this.generatedAt = generatedAt; 
    }
    
    public String getError() { 
        return error; 
    }
    
    public void setError(String error) { 
        this.error = error; 
    }

    // Builder class
    public static class Builder {
        private Boolean success;
        private String message;
        private String projectName;
        private String zipFilePath;
        private String downloadUrl;
        private Long fileSize;
        private LocalDateTime generatedAt;
        private String error;

        public Builder() {
            this.success = false;
            this.generatedAt = LocalDateTime.now();
        }

        public Builder success(Boolean success) {
            this.success = success;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder projectName(String projectName) {
            this.projectName = projectName;
            return this;
        }

        public Builder zipFilePath(String zipFilePath) {
            this.zipFilePath = zipFilePath;
            return this;
        }

        public Builder downloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
            return this;
        }

        public Builder fileSize(Long fileSize) {
            this.fileSize = fileSize;
            return this;
        }

        public Builder generatedAt(LocalDateTime generatedAt) {
            this.generatedAt = generatedAt;
            return this;
        }

        public Builder error(String error) {
            this.error = error;
            return this;
        }

        public GenerationResponse build() {
            if (success == null) {
                throw new IllegalStateException("Success field must be set");
            }
            return new GenerationResponse(this);
        }
    }

    // Méthode builder statique
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "GenerationResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", projectName='" + projectName + '\'' +
                ", zipFilePath='" + zipFilePath + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", fileSize=" + fileSize +
                ", generatedAt=" + generatedAt +
                ", error='" + error + '\'' +
                '}';
    }
}