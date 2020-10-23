package com.example.repository;

import com.example.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeReponsitory extends JpaRepository<Employee, String> {
}
