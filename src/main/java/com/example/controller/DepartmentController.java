package com.example.controller;

import com.example.dto.CompanyDTO;
import com.example.dto.DepartmentDTO;
import com.example.model.Department;
import com.example.service.DepartmentService;
import com.example.utils.CompanyConvert;
import com.example.utils.DepartmentConvert;
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
    @GetMapping("/len2/department")
    public ResponseEntity<Map<String, Object>> getAllDepartment3(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy){
        Map<String, Object> body = new HashMap<>();
        Page<DepartmentDTO> departmentPage= departmentConvert.pageModelToPageDTO(departmentService.getAllDepartmnet2(pageNo,pageSize,sortBy));
        body.put("body", departmentPage.getContent());
        body.put("currentPage", departmentPage.getNumber());
        body.put("totalItems", departmentPage.getTotalElements());
        body.put("totalPages", departmentPage.getTotalPages());
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
    @PostMapping("/department")
    public DepartmentDTO createDepartment(@Valid @RequestBody DepartmentDTO department){
        return departmentConvert.modelToDTO(departmentService.saveDepartment(departmentConvert.dtoToModel(department)));
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
