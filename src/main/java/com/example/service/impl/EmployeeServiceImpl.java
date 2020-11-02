package com.example.service.impl;

import com.example.dto.EmployeeDTO;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Company;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Transactional(rollbackFor = Exception.class)
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
    public List<EmployeeDTO> getAllEmployee() {
        return employeeConvert.listModelToListDTO(employeeReponsitory.findAll());
    }

    @Override
    public Page<EmployeeDTO> getAllEmployeeBySort(Integer pageNo, Integer pageSize, String sortBy, String typeSort) {
        Pageable paging;
        if(typeSort.equals("asc"))
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        else
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        Page<EmployeeDTO> pagedResult = employeeConvert.pageModelToPageDTO(employeeReponsitory.findAll(paging));
        return  pagedResult;
    }

    @Override
    public Optional<EmployeeDTO> getEmployeeById(String id) {
        Employee employee = employeeReponsitory.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Not found any employee"));
        //return ResponseEntity.ok().body(employee);
        return Optional.ofNullable(employeeConvert.modelToDTO(employee));
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
