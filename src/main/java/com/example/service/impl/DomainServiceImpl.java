package com.example.service.impl;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Domain;
import com.example.repository.DomainReponsitory;
import com.example.service.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DomainServiceImpl implements DomainService {
    @Autowired
    private DomainReponsitory domainReponsitory;
    @Override
    public Domain saveDomain(Domain domain) {
        return domainReponsitory.save(domain);
    }

    @Override
    public List<Domain> getAllDomain() {
        return domainReponsitory.findAll();
    }

    @Override
    public Domain getDomainById(String id) {
        Domain domain = domainReponsitory.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Not found any domain"));
        //return ResponseEntity.ok().body(domain);
        return domain;
    }

    @Override
    public Domain updateDomain(String id, Domain domain) {
        Domain domain1 = domainReponsitory.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Not found any domain"));
        domain1.setName(domain.getName());
        final Domain updateDomain = domainReponsitory.save(domain1);
        //return ResponseEntity.ok().body(updateDomain);
        return updateDomain;
    }

    @Override
    public Map<String, Boolean> deleteDomain(String id) {
        Domain domain = domainReponsitory.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Not found any domain"));
        domainReponsitory.delete(domain);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete domain ok", Boolean.TRUE);
        return response;
    }
}
