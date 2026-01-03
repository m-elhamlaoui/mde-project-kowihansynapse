package com.kowihan.acceleo.model.dto;

import com.kowihan.acceleo.model.metamodel.DatabaseConfig;
import com.kowihan.acceleo.model.metamodel.AuthenticationConfig;
import com.kowihan.acceleo.model.metamodel.APIFeatures;
import com.kowihan.acceleo.model.metamodel.Entity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

public class ProjectSpecification {
    @NotBlank(message = "Project name is required")
    private String projectName;
    
    @NotBlank(message = "Framework is required")
    private String framework; // DJANGO, FLASK, FASTAPI
    
    private String description;
    
    private String pythonVersion = "3.9";
    
    @Valid
    private DatabaseConfig database;
    
    @Valid
    private AuthenticationConfig authentication;
    
    @Valid
    private APIFeatures apiFeatures;
    
    @NotEmpty(message = "At least one entity is required")
    @Valid
    private List<Entity> entities = new ArrayList<>();

    // Getters and Setters
    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }

    public String getFramework() { return framework; }
    public void setFramework(String framework) { this.framework = framework; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPythonVersion() { return pythonVersion; }
    public void setPythonVersion(String pythonVersion) { this.pythonVersion = pythonVersion; }

    public DatabaseConfig getDatabase() { return database; }
    public void setDatabase(DatabaseConfig database) { this.database = database; }

    public AuthenticationConfig getAuthentication() { return authentication; }
    public void setAuthentication(AuthenticationConfig authentication) { this.authentication = authentication; }

    public APIFeatures getApiFeatures() { return apiFeatures; }
    public void setApiFeatures(APIFeatures apiFeatures) { this.apiFeatures = apiFeatures; }

    public List<Entity> getEntities() { return entities; }
    public void setEntities(List<Entity> entities) { this.entities = entities; }
}
