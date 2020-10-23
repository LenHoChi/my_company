package com.example.repository;

import com.example.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectReponsitory extends JpaRepository<Project, String> {
}
