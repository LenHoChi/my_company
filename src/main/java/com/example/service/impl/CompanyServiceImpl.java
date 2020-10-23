package com.example.service.impl;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Company;
import com.example.repository.CompanyRepository;
import com.example.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Override
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public List<Company> getAllCompany() {
        return (List<Company>) companyRepository.findAll();
    }

    @Override
    public ResponseEntity<Company> getCompanyById(String id) {
        Company company= companyRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found any company"));
        return ResponseEntity.ok().body(company);
    }

    @Override
    public ResponseEntity<Company> updateCompany(String id, Company company) {
        Company company1 = companyRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not Found any company"));
        company1.setName(company.getName());
        final Company updateComany = companyRepository.save(company1);
        return ResponseEntity.ok().body(updateComany);
    }

    @Override
    public Map<String, Boolean> deleteCompany(String id) {
        Company company = companyRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found any company"));
        companyRepository.delete(company);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete company ok", Boolean.TRUE);
        return response;
    }
}
