package com.example.service;

import com.example.dto.ProjectDTO;
import com.example.model.Company;
import com.example.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProjectService {
    List<ProjectDTO> getAllProject();
    Page<ProjectDTO> getAllProjectBySort(Integer pageNo, Integer pageSize, String sortBy, String typeSort);
    ProjectDTO saveProject(Project project);
    ProjectDTO upateProject(String id, Project project);
    Optional<ProjectDTO> getProjectById(String id);
    Map<String, Boolean> deleteProject(String id);
}
