package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.PatientEntry;
import com.example.demo.service.PatientEntryService;


@RestController
@RequestMapping("/patients")
public class PatientEntryControllerV2 {

    @Autowired
    private PatientEntryService service;

    @PostMapping("/entry")
    public PatientEntry create(@RequestBody PatientEntry pat) {
        return service.savePatient(pat);
    }

    @GetMapping("/entries")
    public List<PatientEntry> getAll() {
        return service.getAll();
    }
    
    //Assign HealthMetric:SPO2,BP..
    @PutMapping("/{id}/HealthMetric/{HealthMetricId}")
    public PatientEntry assignWard(@PathVariable String id,
                                          @PathVariable String HealthMetricId) {
        return service.assignHealthMetric(id, HealthMetricId);
    }
    
    //Assign MonitoringDevice:KinesisCare
    @PutMapping("/{id}/MonitoringDevice")
    public PatientEntry assignMonitoringDevice(@PathVariable String id,
                                        @RequestBody List<String> MonitoringDeviceIds) {
        return service.assignMonitoringDevice(id, MonitoringDeviceIds);
    }
}
