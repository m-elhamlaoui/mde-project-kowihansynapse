package com.kowihan.acceleo.model.metamodel;

import java.util.ArrayList;
import java.util.List;

public class ApplicationModel {
    private String projectName;
    private String framework; // DJANGO, FLASK, FASTAPI
    private String pythonVersion;
    private String description;
    private List<Entity> entities = new ArrayList<>();
    private DatabaseConfig database;
    private AuthenticationConfig authentication;
    private APIFeatures apiFeatures;

    // Getters and Setters
    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }

    public String getFramework() { return framework; }
    public void setFramework(String framework) { this.framework = framework; }

    public String getPythonVersion() { return pythonVersion; }
    public void setPythonVersion(String pythonVersion) { this.pythonVersion = pythonVersion; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<Entity> getEntities() { return entities; }
    public void setEntities(List<Entity> entities) { this.entities = entities; }

    public DatabaseConfig getDatabase() { return database; }
    public void setDatabase(DatabaseConfig database) { this.database = database; }

    public AuthenticationConfig getAuthentication() { return authentication; }
    public void setAuthentication(AuthenticationConfig authentication) { this.authentication = authentication; }

    public APIFeatures getApiFeatures() { return apiFeatures; }
    public void setApiFeatures(APIFeatures apiFeatures) { this.apiFeatures = apiFeatures; }
}
