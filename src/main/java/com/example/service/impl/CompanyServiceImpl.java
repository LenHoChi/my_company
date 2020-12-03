package com.example.service.impl;

import com.example.dto.CompanyDTO;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Company;
import com.example.repository.CompanyRepository;
import com.example.service.CompanyService;
import com.example.utils.convert.CompanyConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Transactional(rollbackFor = Exception.class)
@Service
//@Transactional
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    private CompanyConvert companyConvert;
    @Override
    public CompanyDTO saveCompany(Company company) {
        return companyConvert.modelToDTO(companyRepository.save(company));
    }

    @Override
    public List<CompanyDTO> getAllCompany() {
        return companyConvert.listModelToListDTO(companyRepository.findAll());
    }
    @Override
    public Page<CompanyDTO> getAllCompanyBySort(Integer pageNo, Integer pageSize, String sortBy, String typeSort) {
        Pageable paging;
        if(typeSort.equals("asc"))
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        else
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        Page<CompanyDTO> pagedResult = companyConvert.pageModelToPageDTO(companyRepository.findAll(paging));
        return  pagedResult;
//        if(pagedResult.hasContent()) {
//            return pagedResult.getContent();
//        } else {
//            return new ArrayList<Company>();
//        }
    }

    @Override
    public Optional<CompanyDTO> getCompanyById(String id) {
        Company company= companyRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found any company"));
        //return ResponseEntity.ok().body(company);
        return Optional.ofNullable(companyConvert.modelToDTO(company));
    }

    @Override
    public CompanyDTO updateCompany(String id, Company company) {
        Company company1 = companyRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not Found any company"));
        company1.setName(company.getName());
        final Company updateComany = companyRepository.save(company1);
        //return ResponseEntity.ok().body(updateComany);
        return companyConvert.modelToDTO(updateComany);
    }

    @Override
    public Map<String, Boolean> deleteCompany(String id) {
        Company company = companyRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found any company"));
        companyRepository.delete(company);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete company ok", Boolean.TRUE);
        return response;
    }

    @Override
    public CompanyDTO testTransactional() throws Exception {
        String id="3";
        Company company1 = companyRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not Found any company"));
        company1.setName("hochilen3");
//        final Company updateComany = companyRepository.save(company1);
        return companyConvert.modelToDTO(company1);
    }
}
