package com.example.service.impl;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Company;
import com.example.model.Project;
import com.example.repository.ProjectReponsitory;
import com.example.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectReponsitory projectReponsitory;
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
    public Project saveProject(Project project) {
        return projectReponsitory.save(project);
    }

    @Override
    public Project upateProject(String id, Project project) {
        Project project1 = projectReponsitory.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Not found any project"));
        project1.setName(project.getName());
        final Project updateProject = projectReponsitory.save(project1);
        //return ResponseEntity.ok().body(updateProject);
        return updateProject;
    }

    @Override
    public Project getProjectById(String id) {
        Project project = projectReponsitory.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Not found any project"));
        //return ResponseEntity.ok().body(project);
        return project;
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
