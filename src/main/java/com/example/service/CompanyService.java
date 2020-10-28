package com.example.service;

import com.example.model.Company;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CompanyService {
    Company saveCompany(Company company);
    List<Company> getAllCompany();
    Company getCompanyById(String id);
    Company updateCompany(String id, Company company);
    Map<String, Boolean> deleteCompany(String id);
}
