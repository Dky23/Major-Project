package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.Project;

public interface ProjectRepository extends MongoRepository<Project, String> {}