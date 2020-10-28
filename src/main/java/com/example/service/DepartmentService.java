package com.example.service;

import com.example.model.Department;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Department saveDepartment(Department department);
    List<Department> getAllDepartment();
    Department getDepartmentById(String id);
    Department updateDepartment(String id, Department department);
    Map<String, Boolean> deleteDepartment(String id);
}
