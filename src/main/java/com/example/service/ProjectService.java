package com.example.service;

import com.example.model.Company;
import com.example.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ProjectService {
    List<Project> getAllProject();
    Page<Project> getAllProject2(Integer pageNo, Integer pageSize, String sortBy);
    Project saveProject(Project project);
    Project upateProject(String id, Project project);
    Project getProjectById(String id);
    Map<String, Boolean> deleteProject(String id);
}
