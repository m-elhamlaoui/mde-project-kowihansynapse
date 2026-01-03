package com.kowihan.acceleo.model.metamodel;

public class APIFeatures {
    private Boolean pagination = false;
    private Boolean filtering = false;
    private Boolean swagger = false;
    private Boolean corsEnabled = false;

    // Getters and Setters
    public Boolean getPagination() { return pagination; }
    public void setPagination(Boolean pagination) { this.pagination = pagination; }

    public Boolean getFiltering() { return filtering; }
    public void setFiltering(Boolean filtering) { this.filtering = filtering; }

    public Boolean getSwagger() { return swagger; }
    public void setSwagger(Boolean swagger) { this.swagger = swagger; }

    public Boolean getCorsEnabled() { return corsEnabled; }
    public void setCorsEnabled(Boolean corsEnabled) { this.corsEnabled = corsEnabled; }
}
