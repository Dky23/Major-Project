package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "employee_entries")
public class EmployeeEntry {

    @Id
    private String id;
    private String name;
    private String title;

    // Relationships
    private String departmentId;        // Many-to-One
    private List<String> projectIds;    // Many-to-Many

    public EmployeeEntry() {}

    public EmployeeEntry(String id, String name, String title,
                         String departmentId, List<String> projectIds) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.departmentId = departmentId;
        this.projectIds = projectIds;
    }

    // Getters & Setters

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDepartmentId() { return departmentId; }
    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }

    public List<String> getProjectIds() { return projectIds; }
    public void setProjectIds(List<String> projectIds) { this.projectIds = projectIds; }
}