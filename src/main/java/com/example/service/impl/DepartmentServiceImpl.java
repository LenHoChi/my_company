package com.example.service.impl;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Department;
import com.example.repository.DepartmentReponsitory;
import com.example.service.CompanyService;
import com.example.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentReponsitory departmentReponsitory;
    @Override
    public Department saveDepartment(Department department) {
        return departmentReponsitory.save(department);
    }

    @Override
    public List<Department> getAllDepartment() {
        return departmentReponsitory.findAll();
    }

    @Override
    public Department getDepartmentById(String id) {
        Department department = departmentReponsitory.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not found any department"));
        //return ResponseEntity.ok().body(department);
        return department;
    }

    @Override
    public Department updateDepartment(String id, Department department) {
        Department department1 = departmentReponsitory.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not found any department"));
        department1.setName(department.getName());
        final Department updateDepartment = departmentReponsitory.save(department1);
        //return ResponseEntity.ok().body(updateDepartment);
        return updateDepartment;
    }

    @Override
    public Map<String, Boolean> deleteDepartment(String id) {
        Department department = departmentReponsitory.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found any department"));
        departmentReponsitory.delete(department);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete department ok", Boolean.TRUE);
        return response;
    }
}
