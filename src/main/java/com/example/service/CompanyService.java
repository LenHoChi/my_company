package com.example.service;

import com.example.dto.CompanyDTO;
import com.example.model.Company;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CompanyService {
    CompanyDTO saveCompany(Company company);
    List<CompanyDTO> getAllCompany();
    Page<CompanyDTO> getAllCompanyBySort(Integer pageNo, Integer pageSize, String sortBy, String typeSort);
    Optional<CompanyDTO> getCompanyById(String id);
    CompanyDTO updateCompany(String id, Company company);
    Map<String, Boolean> deleteCompany(String id);

    CompanyDTO  testTransactional() throws Exception;
}
