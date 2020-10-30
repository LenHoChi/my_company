package com.example.controller;

import com.example.dto.CompanyDTO;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Company;
import com.example.service.CompanyService;
import com.example.utils.convert.CompanyConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @GetMapping("/company")
    public List<CompanyDTO> getAllCompany(){
        return CompanyConvert.listModelToListDTO(companyService.getAllCompany());
    }
    @GetMapping("/len/company")
    public Page<CompanyDTO> getAllCompanySortByIdAscending(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy){
        return CompanyConvert.pageModelToPageDTO(companyService.getAllCompanyByIdAscending(pageNo,pageSize,sortBy));
    }
    @GetMapping("/len2/company")
    public ResponseEntity<Map<String, Object>> getAllCompanySortByNameDescending(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "name") String sortBy){
        Map<String, Object> body = new HashMap<>();
        Page<CompanyDTO> companyPage= CompanyConvert.pageModelToPageDTO(companyService.getAllCompanyByNameDecending(pageNo,pageSize,sortBy));
        body.put("body", companyPage.getContent());
        body.put("currentPage", companyPage.getNumber());
        body.put("totalItems", companyPage.getTotalElements());
        body.put("totalPages", companyPage.getTotalPages());
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
    @GetMapping("/company/{id}")
    public CompanyDTO getCompanyById(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        return CompanyConvert.modelToDTO(companyService.getCompanyById(id).get());
    }
    @PostMapping("/company")
    public CompanyDTO createCompany(@Valid @RequestBody CompanyDTO company){
        return  companyService.saveCompany(CompanyConvert.dtoToModel(company));
    }
    @PutMapping("/company/{id}")
    public CompanyDTO updateCompany(@PathVariable(value = "id") String id, @Valid @RequestBody Company company) throws  ResourceNotFoundException{
        return  companyService.updateCompany(id,company);
    }
    @DeleteMapping("/company/{id}")
    public Map<String, Boolean> deleteCompany(@PathVariable(value = "id") String id) throws  ResourceNotFoundException{
        return  companyService.deleteCompany(id);
    }
}
