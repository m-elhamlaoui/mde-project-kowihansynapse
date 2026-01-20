package com.kowihan.acceleo.model.metamodel;

import java.util.ArrayList;
import java.util.List;

public class Index {
    private String name;
    private List<String> fields = new ArrayList<>();
    private Boolean isUnique = false;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<String> getFields() { return fields; }
    public void setFields(List<String> fields) { this.fields = fields; }

    public Boolean getIsUnique() { return isUnique; }
    public void setIsUnique(Boolean isUnique) { this.isUnique = isUnique; }
}
