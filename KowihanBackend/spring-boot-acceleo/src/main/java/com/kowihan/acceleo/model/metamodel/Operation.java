package com.kowihan.acceleo.model.metamodel;

import java.util.ArrayList;
import java.util.List;

public class Operation {
    private String name;
    private String returnType;
    private String visibility; // PUBLIC, PRIVATE, PROTECTED
    private List<Parameter> parameters = new ArrayList<>();

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getReturnType() { return returnType; }
    public void setReturnType(String returnType) { this.returnType = returnType; }

    public String getVisibility() { return visibility; }
    public void setVisibility(String visibility) { this.visibility = visibility; }

    public List<Parameter> getParameters() { return parameters; }
    public void setParameters(List<Parameter> parameters) { this.parameters = parameters; }
}
