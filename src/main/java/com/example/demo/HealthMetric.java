package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "healthmetric")
public class HealthMetric {

    @Id
    private String id;
    private String name;
    
    private List<String> PatientIds;

    public HealthMetric() {
        this.PatientIds = new ArrayList<>();
    }

    public HealthMetric(String id, String name) {
        this.id = id;
        this.name = name;
        this.PatientIds = new ArrayList<>();
    }
    
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public List<String> getPatientIds() { return PatientIds; }
    public void setPatientIds(List<String> PatientIds) { this.PatientIds = PatientIds; }
}
