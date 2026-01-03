package com.kowihan.acceleo.model.metamodel;

public class Relationship {
    private String name;
    private String type; // ONE_TO_ONE, ONE_TO_MANY, MANY_TO_ONE, MANY_TO_MANY
    private String targetEntity;
    private String relatedName;
    private String onDelete; // CASCADE, SET_NULL, PROTECT

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getTargetEntity() { return targetEntity; }
    public void setTargetEntity(String targetEntity) { this.targetEntity = targetEntity; }

    public String getRelatedName() { return relatedName; }
    public void setRelatedName(String relatedName) { this.relatedName = relatedName; }

    public String getOnDelete() { return onDelete; }
    public void setOnDelete(String onDelete) { this.onDelete = onDelete; }
}
