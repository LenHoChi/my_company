package com.example.controller;

import com.example.dto.EmployeeDTO;
import com.example.model.Employee;
import com.example.service.EmployeeService;
import com.example.utils.EmployeeConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    EmployeeConvert employeeConvert=new EmployeeConvert();
    @GetMapping("/employee/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "id") String id){
        return employeeConvert.modelToDTo(employeeService.getEmployeeById(id));
    }
    @GetMapping("/employee")
    public List<EmployeeDTO> getAllEmployee(){
        return employeeConvert.listModelToListDTO(employeeService.getAllEmployee());
    }
    @PostMapping("/employee")
    public EmployeeDTO createEmployee(@Valid @RequestBody Employee employee){
        return employeeConvert.modelToDTo(employeeService.saveEmployee(employee));
    }
    @PutMapping("/employee/{id}")
    public EmployeeDTO updateEmployee(@PathVariable(name = "id") String id, @Valid @RequestBody Employee employee){
        return employeeConvert.modelToDTo(employeeService.updateEmployee(id, employee));
    }
    @DeleteMapping("/employee/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(name = "id") String id){
        return employeeService.deleteEmployee(id);
    }
}
