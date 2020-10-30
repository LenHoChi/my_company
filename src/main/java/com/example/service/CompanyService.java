package com.example.service;

import com.example.dto.CompanyDTO;
import com.example.model.Company;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CompanyService {
    CompanyDTO saveCompany(Company company);
    List<Company> getAllCompany();
    Page<Company> getAllCompanyByIdAscending(Integer pageNo, Integer pageSize, String sortBy);
    Page<Company> getAllCompanyByNameDecending(Integer pageNo, Integer pageSize, String sortBy);
    Optional<Company> getCompanyById(String id);
    CompanyDTO updateCompany(String id, Company company);
    Map<String, Boolean> deleteCompany(String id);
}
