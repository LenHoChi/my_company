package com.example.controller;

import com.example.model.Employee;
import com.example.service.EmployeeService;
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
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(name = "id") String id){
        return employeeService.getEmployeeById(id);
    }
    @GetMapping("/employee")
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }
    @PostMapping("/employee")
    public Employee createEmployee(@Valid @RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }
    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(name = "id") String id, @Valid @RequestBody Employee employee){
        return employeeService.updateEmployee(id, employee);
    }
    @DeleteMapping("/employee/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(name = "id") String id){
        return employeeService.deleteEmployee(id);
    }
}
