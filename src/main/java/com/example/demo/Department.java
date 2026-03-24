package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "departments")
public class Department {

    @Id
    private String id;
    private String name;
    
    private List<String> employeeIds;

    public Department() {
        this.employeeIds = new ArrayList<>();
    }

    public Department(String id, String name) {
        this.id = id;
        this.name = name;
        this.employeeIds = new ArrayList<>();
    }
    
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public List<String> getEmployeeIds() { return employeeIds; }
    public void setEmployeeIds(List<String> employeeIds) { this.employeeIds = employeeIds; }
}