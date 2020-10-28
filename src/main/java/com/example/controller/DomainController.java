package com.example.controller;

import com.example.dto.DomainDTO;
import com.example.model.Domain;
import com.example.service.DomainService;
import com.example.utils.DomainConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @PostMapping("/domain")
    public DomainDTO createDomain(@Valid @RequestBody Domain domain){
        return domainConvert.modelToDTO(domainService.saveDomain(domain));
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
