package com.example.controller;

import com.example.dto.DepartmentDTO;
import com.example.model.Department;
import com.example.service.DepartmentService;
import com.example.utils.CompanyConvert;
import com.example.utils.DepartmentConvert;
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
    DepartmentConvert departmentConvert =new DepartmentConvert();
    @GetMapping("/department")
    public List<DepartmentDTO> getAllDepartment(){
        return departmentConvert.listModelToListDTO(departmentService.getAllDepartment());
    }
    @GetMapping("/department/{id}")
    public DepartmentDTO getDepartmentById(@PathVariable(value = "id") String id){
        return departmentConvert.modelToDTO(departmentService.getDepartmentById(id));
    }
    @PostMapping("/department")
    public DepartmentDTO createDepartment(@Valid @RequestBody Department department){
        return departmentConvert.modelToDTO(departmentService.saveDepartment(department));
    }
    @PutMapping("/department/{id}")
    public DepartmentDTO updateDepartment(@PathVariable(value = "id") String id, @Valid @RequestBody Department department){
        return departmentConvert.modelToDTO(departmentService.updateDepartment(id,department));
    }
    @DeleteMapping("/department/{id}")
    public Map<String, Boolean> deleteDepartment(@PathVariable(value = "id") String id){
        return departmentService.deleteDepartment(id);
    }
}
