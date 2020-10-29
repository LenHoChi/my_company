package com.example.repository;

import com.example.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeReponsitory extends JpaRepository<Employee, String>, PagingAndSortingRepository<Employee, String> {
}
