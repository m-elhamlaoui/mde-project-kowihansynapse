package com.kowihan.acceleo.model.dto;

public class GenerationRequest {
    private ProjectSpecification specification;
    private String xmiFilePath; // Optional: for direct XMI upload

    // Getters and Setters
    public ProjectSpecification getSpecification() { return specification; }
    public void setSpecification(ProjectSpecification specification) { this.specification = specification; }

    public String getXmiFilePath() { return xmiFilePath; }
    public void setXmiFilePath(String xmiFilePath) { this.xmiFilePath = xmiFilePath; }
}
