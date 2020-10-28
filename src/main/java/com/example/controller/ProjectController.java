package com.example.controller;

import com.example.dto.ProjectDTO;
import com.example.model.Project;
import com.example.service.ProjectService;
import com.example.utils.ProjectConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    ProjectConvert projectConvert=new ProjectConvert();
    @GetMapping("/project/{id}")
    public ProjectDTO getProjectById(@PathVariable(name = "id") String id){
        return projectConvert.modelToDTo(projectService.getProjectById(id));
    }
    @GetMapping("/project")
    public List<ProjectDTO> getAllProject(){
        return projectConvert.listModelToListDTO(projectService.getAllProject());
    }
    @PostMapping("/project")
    public ProjectDTO createProject(@Valid @RequestBody Project project){
        return projectConvert.modelToDTo(projectService.saveProject(project));
    }
    @PutMapping("/project/{id}")
    public ProjectDTO updateProject(@PathVariable(name = "id") String id, @Valid @RequestBody Project project){
        return projectConvert.modelToDTo(projectService.upateProject(id,project));
    }
    @DeleteMapping("/project/{id}")
    public Map<String, Boolean> deleteProject(@PathVariable(name = "id") String id){
        return projectService.deleteProject(id);
    }
}
