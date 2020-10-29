package com.example.repository;

import com.example.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentReponsitory extends JpaRepository<Department, String>, PagingAndSortingRepository<Department, String> {

}
