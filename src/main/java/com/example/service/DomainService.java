package com.example.service;

import com.example.model.Company;
import com.example.model.Domain;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface DomainService {
    Domain saveDomain(Domain domain);
    List<Domain> getAllDomain();
    Page<Domain> getAllDomain2(Integer pageNo, Integer pageSize, String sortBy);
    Domain getDomainById(String id);
    Domain updateDomain(String id, Domain domain);
    Map<String, Boolean> deleteDomain(String id);
}
