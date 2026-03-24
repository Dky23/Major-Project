package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "patient_entries")
public class PatientEntry {

    @Id
    private String id;
    private String name;
    private String title;

    // Relationships
    private String HealthMetricId;        // Many-to-One
    private List<String> MonitoringDeviceIds;    // Many-to-Many

    public PatientEntry() {}

    public PatientEntry(String id, String name, String title,
                         String HealthMetricId, List<String> MonitoringDeviceIds) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.HealthMetricId = HealthMetricId;
        this.MonitoringDeviceIds = MonitoringDeviceIds;
    }

    // Getters & Setters

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getHealthMetricId() { return HealthMetricId; }
    public void setHealthMetricId(String HealthMetricId) { this.HealthMetricId = HealthMetricId; }

    public List<String> getMonitoringDeviceIds() { return MonitoringDeviceIds; }
    public void setMonitoringDeviceIds(List<String> MonitoringDeviceIds) { this.MonitoringDeviceIds = MonitoringDeviceIds; }
}
