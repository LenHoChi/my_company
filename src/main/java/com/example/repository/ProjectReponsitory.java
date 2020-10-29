package com.example.repository;

import com.example.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProjectReponsitory extends JpaRepository<Project, String>, PagingAndSortingRepository<Project, String> {
}
