package com.example.service.impl;

import com.example.dto.ProjectDTO;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Project;
import com.example.repository.ProjectReponsitory;
import com.example.service.ProjectService;
import com.example.utils.convert.ProjectConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectReponsitory projectReponsitory;
    private ProjectConvert projectConvert;
    @Override
    public List<Project> getAllProject() {
        return projectReponsitory.findAll();
    }

    @Override
    public Page<Project> getAllProjectByIdAscending(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Project> pagedResult = projectReponsitory.findAll(paging);
        return  pagedResult;
    }

    @Override
    public ProjectDTO saveProject(Project project) {
        return projectConvert.modelToDTO(projectReponsitory.save(project));
    }

    @Override
    public ProjectDTO upateProject(String id, Project project) {
        Project project1 = projectReponsitory.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Not found any project"));
        project1.setName(project.getName());
        final Project updateProject = projectReponsitory.save(project1);
        //return ResponseEntity.ok().body(updateProject);
        return projectConvert.modelToDTO(updateProject);
    }

    @Override
    public Optional<Project> getProjectById(String id) {
        Project project = projectReponsitory.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Not found any project"));
        //return ResponseEntity.ok().body(project);
        return Optional.ofNullable(project);
    }

    @Override
    public Map<String, Boolean> deleteProject(String id) {
        Project project = projectReponsitory.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Not found any project"));
        projectReponsitory.delete(project);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete project ok", Boolean.TRUE);
        return response;
    }
}
