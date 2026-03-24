package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.EmployeeEntry;

public interface EmployeeEntryRepository extends MongoRepository<EmployeeEntry, String> {}
