package com.example.service.impl;

import com.example.dto.ProjectDTO;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Company;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Transactional(rollbackFor = Exception.class)
@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectReponsitory projectReponsitory;
    private ProjectConvert projectConvert;
    @Override
    public List<ProjectDTO> getAllProject() {
        return projectConvert.listModelToListDTO(projectReponsitory.findAll());
    }

    @Override
    public Page<ProjectDTO> getAllProjectBySort(Integer pageNo, Integer pageSize, String sortBy,String typeSort) {
        Pageable paging;
        if(typeSort.equals("asc"))
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        else
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        Page<ProjectDTO> pagedResult = projectConvert.pageModelToPageDTO(projectReponsitory.findAll(paging));
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
    public Optional<ProjectDTO> getProjectById(String id) {
        Project project = projectReponsitory.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Not found any project"));
        //return ResponseEntity.ok().body(project);
        return Optional.ofNullable(projectConvert.modelToDTO(project));
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
