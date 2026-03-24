package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.EmployeeEntry;
import com.example.demo.service.EmployeeEntryService;


@RestController
@RequestMapping("/employees")
public class EmployeeEntryControllerV2 {

    @Autowired
    private EmployeeEntryService service;

    @PostMapping("/entry")
    public EmployeeEntry create(@RequestBody EmployeeEntry emp) {
        return service.saveEmployee(emp);
    }

    @GetMapping("/entries")
    public List<EmployeeEntry> getAll() {
        return service.getAll();
    }
    
    //Assign Department
    @PutMapping("/{id}/department/{deptId}")
    public EmployeeEntry assignDepartment(@PathVariable String id,
                                          @PathVariable String deptId) {
        return service.assignDepartment(id, deptId);
    }
    
    //Assign Project
    @PutMapping("/{id}/projects")
    public EmployeeEntry assignProjects(@PathVariable String id,
                                        @RequestBody List<String> projectIds) {
        return service.assignProjects(id, projectIds);
    }
}


//    //Multiple entries	
//	@PostMapping("/multiple")
//	public boolean createEntries(@RequestBody List<EmployeeEntry> entries) {
//	    for (EmployeeEntry entry : entries) {
//	        // Adding each employee entry to the map
//	        employeeEntries.put(entry.getId(), entry);
//	    }
//	    return true;
//	}