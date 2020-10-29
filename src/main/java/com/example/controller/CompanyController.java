package com.example.controller;

import com.example.dto.CompanyDTO;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Company;
import com.example.service.CompanyService;
import com.example.utils.CompanyConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
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
    @GetMapping("/len/company")
    public Page<CompanyDTO> getAllCompanySortByIdAscending(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy){
        return companyConvert.pageModelToPageDTO(companyService.getAllCompanyByIdAscending(pageNo,pageSize,sortBy));
    }
    @GetMapping("/len2/company")
    public ResponseEntity<Map<String, Object>> getAllCompanySortByNameDescending(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy){
        Map<String, Object> body = new HashMap<>();
        Page<CompanyDTO> companyPage= companyConvert.pageModelToPageDTO(companyService.getAllCompanyByNameDecending(pageNo,pageSize,sortBy));
        body.put("body", companyPage.getContent());
        body.put("currentPage", companyPage.getNumber());
        body.put("totalItems", companyPage.getTotalElements());
        body.put("totalPages", companyPage.getTotalPages());
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
    @GetMapping("/company/{id}")
    public CompanyDTO getCompanyById(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        return companyConvert.modelToDTO(companyService.getCompanyById(id));
    }
    @PostMapping("/company")
    public CompanyDTO createCompany(@Valid @RequestBody CompanyDTO company){
        return  companyConvert.modelToDTO(companyService.saveCompany(companyConvert.dtoToModel(company)));
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
