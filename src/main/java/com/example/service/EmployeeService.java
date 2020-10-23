package com.example.service;

import com.example.model.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployee();
    ResponseEntity<Employee> getEmployeeById(String id);
    ResponseEntity<Employee> updateEmployee(String id, Employee employee);
    Map<String, Boolean> deleteEmployee(String id);
}
