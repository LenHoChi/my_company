package com.example.service;

import com.example.model.Company;
import com.example.model.Department;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Department saveDepartment(Department department);
    List<Department> getAllDepartment();
    Page<Department> getAllDepartmnet2(Integer pageNo, Integer pageSize, String sortBy);
    Department getDepartmentById(String id);
    Department updateDepartment(String id, Department department);
    Map<String, Boolean> deleteDepartment(String id);
}
