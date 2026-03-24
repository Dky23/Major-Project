package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.MonitoringDevice;

public interface MonitoringDeviceRepository extends MongoRepository<MonitoringDevice, String> {}
