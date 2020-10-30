package com.example.service;

import com.example.dto.EmployeeDTO;
import com.example.model.Company;
import com.example.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EmployeeService {
    EmployeeDTO saveEmployee(Employee employee);
    List<Employee> getAllEmployee();
    Page<Employee> getAllEmployeeByIdAscending(Integer pageNo, Integer pageSize, String sortBy);
    Optional<Employee> getEmployeeById(String id);
    EmployeeDTO updateEmployee(String id, Employee employee);
    Map<String, Boolean> deleteEmployee(String id);
}
