package com.example.utils;

import com.example.dto.CompanyDTO;
import com.example.dto.DepartmentDTO;
import com.example.model.Company;
import com.example.model.Department;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class DepartmentConvert {
    ModelMapper modelMapper =new ModelMapper();
    public Department dtoToModel(DepartmentDTO departmentDTO){
        Department department=new Department();
        department.setName(departmentDTO.getName());
        department.setId(departmentDTO.getId());
        department.setDescription(departmentDTO.getDescription());
        department.setEmail(departmentDTO.getEmail());
        return department;
    }
    public DepartmentDTO modelToDTO(Department department){
        DepartmentDTO departmentDTO=modelMapper.map(department,DepartmentDTO.class);
        return departmentDTO;
    }
    public List<DepartmentDTO> listModelToListDTO(List<Department> departments){
        List<DepartmentDTO> departmentDTOS=new ArrayList<>();
        for(int i=0;i<departments.size();i++){
            DepartmentDTO departmentDTO=modelMapper.map(departments.get(i),DepartmentDTO.class);
            departmentDTOS.add(departmentDTO);
        }
        return departmentDTOS;
    }
    public Page<DepartmentDTO> pageModelToPageDTO(Page<Department> pageModel){
        return pageModel.map(this::modelToDTO);
    }
}
