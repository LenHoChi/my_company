package com.example.service.impl;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Company;
import com.example.repository.CompanyRepository;
import com.example.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
    public Page<Company> getAllCompanyByNameDecending(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("name").descending());
        Page<Company> pagedResult = companyRepository.findAll(paging);
        return  pagedResult;
//        if(pagedResult.hasContent()) {
//            return pagedResult.getContent();
//        } else {
//            return new ArrayList<Company>();
//        }
    }

    @Override
    public Company getCompanyById(String id) {
        Company company= companyRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found any company"));
        //return ResponseEntity.ok().body(company);
        return company;
    }

    @Override
    public Company updateCompany(String id, Company company) {
        Company company1 = companyRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not Found any company"));
        company1.setName(company.getName());
        final Company updateComany = companyRepository.save(company1);
        //return ResponseEntity.ok().body(updateComany);
        return updateComany;
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
