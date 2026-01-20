package com.kowihan.acceleo.model.metamodel;

import java.util.ArrayList;
import java.util.List;

public class Entity {
    private String name;
    private String tableName;
    private String description;
    private Boolean isAbstract = false;
    private List<Attribute> attributes = new ArrayList<>();
    private List<Relationship> relationships = new ArrayList<>();
    private List<Operation> operations = new ArrayList<>();
    private List<Index> indexes = new ArrayList<>();
    private Entity parentEntity;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTableName() { return tableName; }
    public void setTableName(String tableName) { this.tableName = tableName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Boolean getIsAbstract() { return isAbstract; }
    public void setIsAbstract(Boolean isAbstract) { this.isAbstract = isAbstract; }

    public List<Attribute> getAttributes() { return attributes; }
    public void setAttributes(List<Attribute> attributes) { this.attributes = attributes; }

    public List<Relationship> getRelationships() { return relationships; }
    public void setRelationships(List<Relationship> relationships) { this.relationships = relationships; }

    public List<Operation> getOperations() { return operations; }
    public void setOperations(List<Operation> operations) { this.operations = operations; }

    public List<Index> getIndexes() { return indexes; }
    public void setIndexes(List<Index> indexes) { this.indexes = indexes; }

    public Entity getParentEntity() { return parentEntity; }
    public void setParentEntity(Entity parentEntity) { this.parentEntity = parentEntity; }
}
