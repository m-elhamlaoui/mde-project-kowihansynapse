package com.kowihan.acceleo.model.metamodel;

public class DatabaseConfig {
    private String type; // POSTGRESQL, MYSQL, SQLITE, MONGODB
    private String host;
    private Integer port;
    private String name;

    // Getters and Setters
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getHost() { return host; }
    public void setHost(String host) { this.host = host; }

    public Integer getPort() { return port; }
    public void setPort(Integer port) { this.port = port; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
