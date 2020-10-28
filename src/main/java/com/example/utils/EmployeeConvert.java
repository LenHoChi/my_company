package com.example.utils;

import com.example.dto.DepartmentDTO;
import com.example.dto.EmployeeDTO;
import com.example.model.Department;
import com.example.model.Employee;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class EmployeeConvert {
    ModelMapper modelMapper =new ModelMapper();
    public EmployeeDTO modelToDTo(Employee employee){
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
}
