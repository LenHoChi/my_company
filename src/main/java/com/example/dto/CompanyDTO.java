package com.example.dto;

import com.example.model.Department;
import lombok.Data;

import java.util.Set;

@Data
public class CompanyDTO {
    private String id;
    private String name;
    private Set<Department> departments;
}
