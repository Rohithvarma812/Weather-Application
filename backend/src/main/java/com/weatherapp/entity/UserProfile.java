package com.weatherapp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("user_profiles")
public class UserProfile {
    @Id
    private Long id;
    private String username;
    private String passwordHash;
    private double heatThreshold;
    private double pollutionThreshold;
    private boolean asthma;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public double getHeatThreshold() { return heatThreshold; }
    public void setHeatThreshold(double heatThreshold) { this.heatThreshold = heatThreshold; }
    public double getPollutionThreshold() { return pollutionThreshold; }
    public void setPollutionThreshold(double pollutionThreshold) { this.pollutionThreshold = pollutionThreshold; }
    public boolean isAsthma() { return asthma; }
    public void setAsthma(boolean asthma) { this.asthma = asthma; }
}
