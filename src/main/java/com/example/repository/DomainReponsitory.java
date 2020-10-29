package com.example.repository;

import com.example.model.Domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainReponsitory extends JpaRepository<Domain, String>, PagingAndSortingRepository<Domain, String> {
}
