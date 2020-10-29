package com.example.controller;

import com.example.dto.CompanyDTO;
import com.example.dto.DomainDTO;
import com.example.model.Domain;
import com.example.service.DomainService;
import com.example.utils.DomainConvert;
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
public class DomainController {
    @Autowired
    private DomainService domainService;
    DomainConvert domainConvert=new DomainConvert();
    @GetMapping("/domain")
    public List<DomainDTO> getAllDomain(){
        return domainConvert.listModelToListDTO(domainService.getAllDomain());
    }
    @GetMapping("/domain/{id}")
    public DomainDTO getDomainById(@PathVariable(name = "id") String id){
        return domainConvert.modelToDTO(domainService.getDomainById(id));
    }
    @GetMapping("/len2/domain")
    public ResponseEntity<Map<String, Object>> getAllDomainByIdAscending(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy){
        Map<String, Object> body = new HashMap<>();
        Page<DomainDTO> domainPage= domainConvert.pageModelToPageDTO(domainService.getAllDomainByIdAscending(pageNo,pageSize,sortBy));
        body.put("body", domainPage.getContent());
        body.put("currentPage", domainPage.getNumber());
        body.put("totalItems", domainPage.getTotalElements());
        body.put("totalPages", domainPage.getTotalPages());
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
    @PostMapping("/domain")
    public DomainDTO createDomain(@Valid @RequestBody DomainDTO domain){
        return domainConvert.modelToDTO(domainService.saveDomain(domainConvert.dtoToModel(domain)));
    }
    @PutMapping("/domain/{id}")
    public DomainDTO updateDomain(@PathVariable(name = "id") String id, @Valid @RequestBody Domain domain){
        return domainConvert.modelToDTO(domainService.updateDomain(id,domain));
    }
    @DeleteMapping("/domain/{id}")
    public Map<String, Boolean> deleteDomain(@PathVariable(name = "id") String id){
        return domainService.deleteDomain(id);
    }
}
