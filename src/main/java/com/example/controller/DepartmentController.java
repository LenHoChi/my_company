package com.example.controller;

import com.example.model.Department;
import com.example.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @GetMapping("/department")
    public List<Department> getAllDepartment(){
        return departmentService.getAllDepartment();
    }
    @GetMapping("/department/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable(value = "id") String id){
        return departmentService.getDepartmentById(id);
    }
    @PostMapping("/department")
    public Department createDepartment(@Valid @RequestBody Department department){
        return departmentService.saveDepartment(department);
    }
    @PutMapping("/department/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable(value = "id") String id, @Valid @RequestBody Department department){
        return departmentService.updateDepartment(id,department);
    }
    @DeleteMapping("/department/{id}")
    public Map<String, Boolean> deleteDepartment(@PathVariable(value = "id") String id){
        return departmentService.deleteDepartment(id);
    }
}
