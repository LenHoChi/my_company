package com.example.controller;

import com.example.model.Project;
import com.example.service.ProjectService;
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
    @GetMapping("/project/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable(name = "id") String id){
        return projectService.getProjectById(id);
    }
    @GetMapping("/project")
    public List<Project> getAllProject(){
        return projectService.getAllProject();
    }
    @PostMapping("/project")
    public Project createProject(@Valid @RequestBody Project project){
        return projectService.saveProject(project);
    }
    @PutMapping("/project/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable(name = "id") String id, @Valid @RequestBody Project project){
        return projectService.upateProject(id,project);
    }
    @DeleteMapping("/project/{id}")
    public Map<String, Boolean> deleteProject(@PathVariable(name = "id") String id){
        return projectService.deleteProject(id);
    }
}
