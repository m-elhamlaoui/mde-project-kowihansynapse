package com.kowihan.acceleo.model.metamodel;

public class Attribute {
    private String name;
    private String type; // STRING, INTEGER, FLOAT, BOOLEAN, DATE, DATETIME, etc.
    private Boolean isPrimaryKey = false;
    private Boolean isNullable = true;
    private Boolean isUnique = false;
    private Integer maxLength;
    private Integer minValue;
    private Integer maxValue;
    private String defaultValue;
    private String helpText;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Boolean getIsPrimaryKey() { return isPrimaryKey; }
    public void setIsPrimaryKey(Boolean isPrimaryKey) { this.isPrimaryKey = isPrimaryKey; }

    public Boolean getIsNullable() { return isNullable; }
    public void setIsNullable(Boolean isNullable) { this.isNullable = isNullable; }

    public Boolean getIsUnique() { return isUnique; }
    public void setIsUnique(Boolean isUnique) { this.isUnique = isUnique; }

    public Integer getMaxLength() { return maxLength; }
    public void setMaxLength(Integer maxLength) { this.maxLength = maxLength; }

    public Integer getMinValue() { return minValue; }
    public void setMinValue(Integer minValue) { this.minValue = minValue; }

    public Integer getMaxValue() { return maxValue; }
    public void setMaxValue(Integer maxValue) { this.maxValue = maxValue; }

    public String getDefaultValue() { return defaultValue; }
    public void setDefaultValue(String defaultValue) { this.defaultValue = defaultValue; }

    public String getHelpText() { return helpText; }
    public void setHelpText(String helpText) { this.helpText = helpText; }
}
