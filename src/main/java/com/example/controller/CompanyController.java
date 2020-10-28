package com.example.controller;

import com.example.dto.CompanyDTO;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Company;
import com.example.service.CompanyService;
import com.example.utils.CompanyConvert;
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
    CompanyConvert companyConvert=new CompanyConvert();
    @GetMapping("/company")
    public List<CompanyDTO> getAllCompany(){
        return companyConvert.listModelToListDTO(companyService.getAllCompany());
    }
    @GetMapping("/company/{id}")
    public CompanyDTO getCompanyById(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        return companyConvert.modelToDTO(companyService.getCompanyById(id));
    }
    @PostMapping("/company")
    public CompanyDTO createCompany(@Valid @RequestBody Company company){
        return  companyConvert.modelToDTO(companyService.saveCompany(company));
    }
    @PutMapping("/company/{id}")
    public CompanyDTO updateCompany(@PathVariable(value = "id") String id, @Valid @RequestBody Company company) throws  ResourceNotFoundException{
        return  companyConvert.modelToDTO(companyService.updateCompany(id,company));
    }
    @DeleteMapping("/company/{id}")
    public Map<String, Boolean> deleteCompany(@PathVariable(value = "id") String id) throws  ResourceNotFoundException{
        return  companyService.deleteCompany(id);
    }
}
