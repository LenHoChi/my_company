package com.example.service.impl;

import com.example.dto.EmployeeDTO;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Employee;
import com.example.repository.EmployeeReponsitory;
import com.example.service.EmployeeService;
import com.example.utils.convert.EmployeeConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeReponsitory employeeReponsitory;
    private EmployeeConvert employeeConvert;
    @Override
    public EmployeeDTO saveEmployee(Employee employee) {
        return employeeConvert.modelToDTO(employeeReponsitory.save(employee));
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeReponsitory.findAll();
    }

    @Override
    public Page<Employee> getAllEmployeeByIdAscending(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Employee> pagedResult = employeeReponsitory.findAll(paging);
        return  pagedResult;
    }

    @Override
    public Optional<Employee> getEmployeeById(String id) {
        Employee employee = employeeReponsitory.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Not found any employee"));
        //return ResponseEntity.ok().body(employee);
        return Optional.ofNullable(employee);
    }

    @Override
    public EmployeeDTO updateEmployee(String id, Employee employee) {
        Employee employee1 = employeeReponsitory.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Not found any employee"));
        employee1.setName(employee.getName());
        final Employee updateEmployee = employeeReponsitory.save(employee1);
        //return ResponseEntity.ok().body(updateEmployee);
        return employeeConvert.modelToDTO(updateEmployee);
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
