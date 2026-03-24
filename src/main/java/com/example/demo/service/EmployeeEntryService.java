package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Department;
import com.example.demo.EmployeeEntry;
import com.example.demo.Project;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeEntryRepository;
import com.example.demo.repository.ProjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeEntryService {

    @Autowired
    private EmployeeEntryRepository employeeRepo;

    @Autowired
    private DepartmentRepository departmentRepo;

    @Autowired
    private ProjectRepository projectRepo;

    // CREATE employee
    public EmployeeEntry saveEmployee(EmployeeEntry employee) {
        if (employee.getId() == null) {
            employee.setId(UUID.randomUUID().toString());
        }
        if (employee.getProjectIds() == null) {
            employee.setProjectIds(new ArrayList<>());
        }
        return employeeRepo.save(employee);
    }

    // GET all
    public List<EmployeeEntry> getAll() {
        return employeeRepo.findAll();
    }

    // Assign department (Many-to-One)
    public EmployeeEntry assignDepartment(String empId, String deptId) {
        EmployeeEntry emp = employeeRepo.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Department newDept = departmentRepo.findById(deptId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        // Remove from old department if exists
        if (emp.getDepartmentId() != null) {
            Department oldDept = departmentRepo.findById(emp.getDepartmentId()).orElse(null);
            if (oldDept != null && oldDept.getEmployeeIds() != null) {
                oldDept.getEmployeeIds().remove(empId);
                departmentRepo.save(oldDept);
            }
        }

        // Assign new department to employee
        emp.setDepartmentId(deptId);

        // Add employee to new department
        if (newDept.getEmployeeIds() == null) {
            newDept.setEmployeeIds(new ArrayList<>());
        }
        if (!newDept.getEmployeeIds().contains(empId)) {
            newDept.getEmployeeIds().add(empId);
        }
        departmentRepo.save(newDept);

        return employeeRepo.save(emp);
    }

    // Assign projects (Many-to-Many)
    public EmployeeEntry assignProjects(String empId, List<String> projectIds) {
        EmployeeEntry emp = employeeRepo.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        List<Project> projects = projectRepo.findAllById(projectIds);
        if (projects.size() != projectIds.size()) {
            throw new RuntimeException("Invalid project IDs");
        }

        // Remove employee from old projects
        if (emp.getProjectIds() != null) {
            List<Project> oldProjects = projectRepo.findAllById(emp.getProjectIds());
            for (Project p : oldProjects) {
                if (p.getEmployeeIds() != null) {
                    p.getEmployeeIds().remove(empId);
                    projectRepo.save(p);
                }
            }
        }

        // Assign new projects to employee
        emp.setProjectIds(projectIds);

        // Add employee to new projects
        for (Project p : projects) {
            if (p.getEmployeeIds() == null) {
                p.setEmployeeIds(new ArrayList<>());
            }
            if (!p.getEmployeeIds().contains(empId)) {
                p.getEmployeeIds().add(empId);
                projectRepo.save(p);
            }
        }

        return employeeRepo.save(emp);
    }
}
