package com.example.controller;

import com.example.dto.EmployeeDTO;
import com.example.model.Employee;
import com.example.service.EmployeeService;
import com.example.utils.convert.EmployeeConvert;
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
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/employee/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "id") String id){
        return EmployeeConvert.modelToDTO(employeeService.getEmployeeById(id).get());
    }
    @GetMapping("/employee")
    public List<EmployeeDTO> getAllEmployee(){
        return EmployeeConvert.listModelToListDTO(employeeService.getAllEmployee());
    }
    @GetMapping("/len2/employee")
    public ResponseEntity<Map<String, Object>> getAllEmployeeByIdAscending(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy){
        Map<String, Object> body = new HashMap<>();
        Page<EmployeeDTO> employeePage= EmployeeConvert.pageModelToPageDTO(employeeService.getAllEmployeeByIdAscending(pageNo,pageSize,sortBy));
        body.put("body", employeePage.getContent());
        body.put("currentPage", employeePage.getNumber());
        body.put("totalItems", employeePage.getTotalElements());
        body.put("totalPages", employeePage.getTotalPages());
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
    @PostMapping("/employee")
    public EmployeeDTO createEmployee(@Valid @RequestBody EmployeeDTO employee){
        return employeeService.saveEmployee(EmployeeConvert.dtoToModel(employee));
    }
    @PutMapping("/employee/{id}")
    public EmployeeDTO updateEmployee(@PathVariable(name = "id") String id, @Valid @RequestBody Employee employee){
        return employeeService.updateEmployee(id, employee);
    }
    @DeleteMapping("/employee/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(name = "id") String id){
        return employeeService.deleteEmployee(id);
    }
}
