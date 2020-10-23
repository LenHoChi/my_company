package com.example.service;

import com.example.model.Domain;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface DomainService {
    Domain saveDomain(Domain domain);
    List<Domain> getAllDomain();
    ResponseEntity<Domain> getDomainById(String id);
    ResponseEntity<Domain> updateDomain(String id, Domain domain);
    Map<String, Boolean> deleteDomain(String id);
}
