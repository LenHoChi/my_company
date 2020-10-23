package com.example.service;

import com.example.model.Project;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ProjectService {
    List<Project> getAllProject();
    Project saveProject(Project project);
    ResponseEntity<Project> upateProject(String id, Project project);
    ResponseEntity<Project> getProjectById(String id);
    Map<String, Boolean> deleteProject(String id);
}
