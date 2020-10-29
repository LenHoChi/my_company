package com.example.controller;

import com.example.dto.EmployeeDTO;
import com.example.dto.ProjectDTO;
import com.example.model.Project;
import com.example.service.ProjectService;
import com.example.utils.ProjectConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import javax.validation.Valid;
import java.util.HashMap;
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
        return projectConvert.modelToDTO(projectService.getProjectById(id));
    }
    @GetMapping("/project")
    public List<ProjectDTO> getAllProject(){
        return projectConvert.listModelToListDTO(projectService.getAllProject());
    }
    @GetMapping("/len2/project")
    public ResponseEntity<Map<String, Object>> getAllProject3(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy){
        Map<String, Object> body = new HashMap<>();
        Page<ProjectDTO> projectPage= projectConvert.pageModelToPageDTO(projectService.getAllProject2(pageNo,pageSize,sortBy));
        body.put("body", projectPage.getContent());
        body.put("currentPage", projectPage.getNumber());
        body.put("totalItems", projectPage.getTotalElements());
        body.put("totalPages", projectPage.getTotalPages());
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
    @PostMapping("/project")
    public ProjectDTO createProject(@Valid @RequestBody ProjectDTO project){
        return projectConvert.modelToDTO(projectService.saveProject(projectConvert.dtoToModel(project)));
    }
    @PutMapping("/project/{id}")
    public ProjectDTO updateProject(@PathVariable(name = "id") String id, @Valid @RequestBody Project project){
        return projectConvert.modelToDTO(projectService.upateProject(id,project));
    }
    @DeleteMapping("/project/{id}")
    public Map<String, Boolean> deleteProject(@PathVariable(name = "id") String id){
        return projectService.deleteProject(id);
    }
}
