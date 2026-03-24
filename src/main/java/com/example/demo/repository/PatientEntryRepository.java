package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.PatientEntry;

public interface PatientEntryRepository extends MongoRepository<PatientEntry, String> {}
