package com.example.service;

import com.example.dto.DomainDTO;
import com.example.model.Company;
import com.example.model.Domain;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DomainService {
    DomainDTO saveDomain(Domain domain);
    List<DomainDTO> getAllDomain();
    Page<DomainDTO> getAllDomainBySort(Integer pageNo, Integer pageSize, String sortBy, String typeSort);
    Optional<DomainDTO> getDomainById(String id);
    DomainDTO updateDomain(String id, Domain domain);
    Map<String, Boolean> deleteDomain(String id);
}
