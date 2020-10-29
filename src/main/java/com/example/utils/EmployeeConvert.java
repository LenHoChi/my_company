package com.example.utils;

import com.example.dto.CompanyDTO;
import com.example.dto.DepartmentDTO;
import com.example.dto.EmployeeDTO;
import com.example.model.Company;
import com.example.model.Department;
import com.example.model.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class EmployeeConvert {
    ModelMapper modelMapper =new ModelMapper();
    public Employee dtoToModel(EmployeeDTO employeeDTO){
        Employee employee=new Employee();
        employee.setName(employeeDTO.getName());
        employee.setId(employeeDTO.getId());
        employee.setBirthday(employeeDTO.getBirthday());
        employee.setGender(employeeDTO.getGender());
        employee.setPhone(employeeDTO.getPhone());
        return employee;
    }
    public EmployeeDTO modelToDTO(Employee employee){
        EmployeeDTO employeeDTO=modelMapper.map(employee,EmployeeDTO.class);
        return employeeDTO;
    }
    public List<EmployeeDTO> listModelToListDTO(List<Employee> employees){
        List<EmployeeDTO> employeeDTOS=new ArrayList<>();
        for(int i=0;i<employees.size();i++){
            EmployeeDTO employeeDTO=modelMapper.map(employees.get(i), EmployeeDTO.class);
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }
    public Page<EmployeeDTO> pageModelToPageDTO(Page<Employee> pageModel){
        return pageModel.map(this::modelToDTO);
    }
}
