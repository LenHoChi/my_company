package com.example.service.impl;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Employee;
import com.example.repository.EmployeeReponsitory;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeReponsitory employeeReponsitory;
    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeReponsitory.save(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeReponsitory.findAll();
    }

    @Override
    public Employee getEmployeeById(String id) {
        Employee employee = employeeReponsitory.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Not found any employee"));
        //return ResponseEntity.ok().body(employee);
        return employee;
    }

    @Override
    public Employee updateEmployee(String id, Employee employee) {
        Employee employee1 = employeeReponsitory.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Not found any employee"));
        employee1.setName(employee.getName());
        final Employee updateEmployee = employeeReponsitory.save(employee1);
        //return ResponseEntity.ok().body(updateEmployee);
        return updateEmployee;
    }

    @Override
    public Map<String, Boolean> deleteEmployee(String id) {
        Employee employee = employeeReponsitory.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Not found any employee"));
        employeeReponsitory.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete employee ok",Boolean.TRUE);
        return response;
    }
}
