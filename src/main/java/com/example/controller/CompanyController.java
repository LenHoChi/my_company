package com.example.controller;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Company;
import com.example.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @GetMapping("/company")
    public List<Company> getAllCompany(){
        return companyService.getAllCompany();
    }
    @GetMapping("/company/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        return companyService.getCompanyById(id);
    }
    @PostMapping("/company")
    public Company createCompany(@Valid @RequestBody Company company){
        return  companyService.saveCompany(company);
    }
    @PutMapping("/company/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable(value = "id") String id, @Valid @RequestBody Company company) throws  ResourceNotFoundException{
        return  companyService.updateCompany(id,company);
    }
    @DeleteMapping("/company/{id}")
    public Map<String, Boolean> deleteCompany(@PathVariable(value = "id") String id) throws  ResourceNotFoundException{
        return  companyService.deleteCompany(id);
    }
}
