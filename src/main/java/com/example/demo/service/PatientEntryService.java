package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.HealthMetric;
import com.example.demo.PatientEntry;
import com.example.demo.MonitoringDevice;
import com.example.demo.repository.HealthMetricRepository;
import com.example.demo.repository.PatientEntryRepository;
import com.example.demo.repository.MonitoringDeviceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PatientEntryService {

    @Autowired
    private PatientEntryRepository PatientRepo;

    @Autowired
    private HealthMetricRepository HealthMetricRepo;

    @Autowired
    private MonitoringDeviceRepository MonitoringDeviceRepo;

    // CREATE Patient
    public PatientEntry savePatient(PatientEntry Patient) {
        if (Patient.getId() == null) {
            Patient.setId(UUID.randomUUID().toString());
        }
        if (Patient.getMonitoringDeviceIds() == null) {
            Patient.setMonitoringDeviceIds(new ArrayList<>());
        }
        return PatientRepo.save(Patient);
    }

    // GET all
    public List<PatientEntry> getAll() {
        return PatientRepo.findAll();
    }

    // Assign HealthMetric (Many-to-One)
    public PatientEntry assignHealthMetric(String patId, String HealthMetricId) {
        PatientEntry pat = PatientRepo.findById(patId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        HealthMetric newHealthMetric = HealthMetricRepo.findById(HealthMetricId)
                .orElseThrow(() -> new RuntimeException("HealthMetric not found"));

        // Remove from old HealthMetric if exists
        if (pat.getHealthMetricId() != null) {
            HealthMetric oldHealthMetric = HealthMetricRepo.findById(pat.getHealthMetricId()).orElse(null);
            if (oldHealthMetric != null && oldHealthMetric.getPatientIds() != null) {
                oldHealthMetric.getPatientIds().remove(patId);
                HealthMetricRepo.save(oldHealthMetric);
            }
        }

        // Assign new HealthMetric to Patient
        pat.setHealthMetricId(HealthMetricId);

        // Add Patient to new HealthMetric
        if (newHealthMetric.getPatientIds() == null) {
            newHealthMetric.setPatientIds(new ArrayList<>());
        }
        if (!newHealthMetric.getPatientIds().contains(patId)) {
            newHealthMetric.getPatientIds().add(patId);
        }
        HealthMetricRepo.save(newHealthMetric);

        return PatientRepo.save(pat);
    }

    // Assign MonitoringDevices (Many-to-Many)
    public PatientEntry assignMonitoringDevices(String patId, List<String> MonitoringDeviceIds) {
        PatientEntry pat = PatientRepo.findById(patId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        List<MonitoringDevice> MonitoringDevices = MonitoringDeviceRepo.findAllById(MonitoringDeviceIds);
        if (MonitoringDevices.size() != MonitoringDeviceIds.size()) {
            throw new RuntimeException("Invalid MonitoringDevice IDs");
        }

        // Remove Patient from old MonitoringDevices
        if (pat.getMonitoringDeviceIds() != null) {
            List<MonitoringDevice> oldMonitoringDevices = MonitoringDeviceRepo.findAllById(pat.getMonitoringDeviceIds());
            for (MonitoringDevice p : oldMonitoringDevices) {
                if (p.getPatientIds() != null) {
                    p.getPatientIds().remove(patId);
                    MonitoringDeviceRepo.save(p);
                }
            }
        }

        // Assign new MonitoringDevices to Patient
        pat.setMonitoringDeviceIds(MonitoringDeviceIds);

        // Add Patient to new MonitoringDevices
        for (MonitoringDevice p : MonitoringDevices) {
            if (p.getPatientIds() == null) {
                p.setPatientIds(new ArrayList<>());
            }
            if (!p.getPatientIds().contains(patId)) {
                p.getPatientIds().add(patId);
                MonitoringDeviceRepo.save(p);
            }
        }

        return PatientRepo.save(pat);
    }
}
