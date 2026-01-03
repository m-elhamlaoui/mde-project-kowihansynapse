package com.kowihan.acceleo.model.metamodel;

public class AuthenticationConfig {
    private Boolean enabled = false;
    private String method; // JWT, SESSION, OAUTH2, BASIC
    private Integer tokenExpiryMinutes = 60;

    // Getters and Setters
    public Boolean getEnabled() { return enabled; }
    public void setEnabled(Boolean enabled) { this.enabled = enabled; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }

    public Integer getTokenExpiryMinutes() { return tokenExpiryMinutes; }
    public void setTokenExpiryMinutes(Integer tokenExpiryMinutes) { this.tokenExpiryMinutes = tokenExpiryMinutes; }
}
