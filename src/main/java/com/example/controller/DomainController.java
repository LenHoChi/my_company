package com.example.controller;

import com.example.model.Domain;
import com.example.service.DomainService;
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
    @GetMapping("/domain")
    public List<Domain> getAllDomain(){
        return domainService.getAllDomain();
    }
    @GetMapping("/domain/{id}")
    public ResponseEntity<Domain> getDomainById(@PathVariable(name = "id") String id){
        return domainService.getDomainById(id);
    }
    @PostMapping("/domain")
    public Domain createDomain(@Valid @RequestBody Domain domain){
        return domainService.saveDomain(domain);
    }
    @PutMapping("/domain/{id}")
    public ResponseEntity<Domain> updateDomain(@PathVariable(name = "id") String id, @Valid @RequestBody Domain domain){
        return domainService.updateDomain(id,domain);
    }
    @DeleteMapping("/domain/{id}")
    public Map<String, Boolean> deleteDomain(@PathVariable(name = "id") String id){
        return domainService.deleteDomain(id);
    }
}
