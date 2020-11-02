package com.example.service;

import com.example.dto.DepartmentDTO;
import com.example.model.Company;
import com.example.model.Department;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DepartmentService {
    DepartmentDTO saveDepartment(Department department);
    List<DepartmentDTO> getAllDepartment();
    Page<DepartmentDTO> getAllDepartmnetBySort(Integer pageNo, Integer pageSize, String sortBy, String typeSort);
    Optional<DepartmentDTO> getDepartmentById(String id);
    DepartmentDTO updateDepartment(String id, Department department);
    Map<String, Boolean> deleteDepartment(String id);
}
