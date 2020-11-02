package com.example.service.impl;

import com.example.dto.DepartmentDTO;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Company;
import com.example.model.Department;
import com.example.repository.DepartmentReponsitory;
import com.example.service.DepartmentService;
import com.example.utils.convert.DepartmentConvert;
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
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentReponsitory departmentReponsitory;
    private DepartmentConvert departmentConvert;
    @Override
    public DepartmentDTO saveDepartment(Department department) {
        return departmentConvert.modelToDTO(departmentReponsitory.save(department));
    }

    @Override
    public List<DepartmentDTO> getAllDepartment() {
        return departmentConvert.listModelToListDTO(departmentReponsitory.findAll());
    }

    @Override
    public Page<DepartmentDTO> getAllDepartmnetBySort(Integer pageNo, Integer pageSize, String sortBy, String typeSort) {
        Pageable paging;
        if(typeSort.equals("asc"))
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        else
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        Page<DepartmentDTO> pagedResult = departmentConvert.pageModelToPageDTO(departmentReponsitory.findAll(paging));
        return  pagedResult;
    }

    @Override
    public Optional<DepartmentDTO> getDepartmentById(String id) {
        Department department = departmentReponsitory.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not found any department"));
        //return ResponseEntity.ok().body(department);
        return Optional.ofNullable(departmentConvert.modelToDTO(department));
    }

    @Override
    public DepartmentDTO updateDepartment(String id, Department department) {
        Department department1 = departmentReponsitory.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not found any department"));
        department1.setName(department.getName());
        final Department updateDepartment = departmentReponsitory.save(department1);
        //return ResponseEntity.ok().body(updateDepartment);
        return departmentConvert.modelToDTO(updateDepartment);
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
