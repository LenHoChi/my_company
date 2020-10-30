package com.example.controller;

import com.example.dto.ProjectDTO;
import com.example.model.Project;
import com.example.service.ProjectService;
import com.example.utils.convert.ProjectConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @GetMapping("/project/{id}")
    public ProjectDTO getProjectById(@PathVariable(name = "id") String id){
        return ProjectConvert.modelToDTO(projectService.getProjectById(id).get());
    }
    @GetMapping("/project")
    public List<ProjectDTO> getAllProject(){
        return ProjectConvert.listModelToListDTO(projectService.getAllProject());
    }
    @GetMapping("/len2/project")
    public ResponseEntity<Map<String, Object>> getAllProjectByIdAscending(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy){
        Map<String, Object> body = new HashMap<>();
        Page<ProjectDTO> projectPage= ProjectConvert.pageModelToPageDTO(projectService.getAllProjectByIdAscending(pageNo,pageSize,sortBy));
        body.put("body", projectPage.getContent());
        body.put("currentPage", projectPage.getNumber());
        body.put("totalItems", projectPage.getTotalElements());
        body.put("totalPages", projectPage.getTotalPages());
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
    @PostMapping("/project")
    public ProjectDTO createProject(@Valid @RequestBody ProjectDTO project){
        return projectService.saveProject(ProjectConvert.dtoToModel(project));
    }
    @PutMapping("/project/{id}")
    public ProjectDTO updateProject(@PathVariable(name = "id") String id, @Valid @RequestBody Project project){
        return projectService.upateProject(id,project);
    }
    @DeleteMapping("/project/{id}")
    public Map<String, Boolean> deleteProject(@PathVariable(name = "id") String id){
        return projectService.deleteProject(id);
    }
}
