package com.example.service.impl;

import com.example.dto.DomainDTO;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Company;
import com.example.model.Domain;
import com.example.repository.DomainReponsitory;
import com.example.service.DomainService;
import com.example.utils.convert.DomainConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Transactional(rollbackFor = Exception.class)
@Service
public class DomainServiceImpl implements DomainService {
    @Autowired
    private DomainReponsitory domainReponsitory;
    private DomainConvert domainConvert;
    @Override
    public DomainDTO saveDomain(Domain domain) {
        return domainConvert.modelToDTO(domainReponsitory.save(domain));
    }

    @Override
    public List<DomainDTO> getAllDomain() {
        return domainConvert.listModelToListDTO(domainReponsitory.findAll());
    }

    @Override
    public List<Domain> getAllDomain2() {
        return domainReponsitory.findAll();
    }

    @Override
    public Page<DomainDTO> getAllDomainBySort(Integer pageNo, Integer pageSize, String sortBy, String typeSort) {
        Pageable paging;
        if(typeSort.equals("asc"))
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        else
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        Page<DomainDTO> pagedResult = domainConvert.pageModelToPageDTO(domainReponsitory.findAll(paging));
        return  pagedResult;
    }

    @Override
    public Optional<DomainDTO> getDomainById(String id) {
        Domain domain = domainReponsitory.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Not found any domain"));
        //return ResponseEntity.ok().body(domain);
        return Optional.ofNullable(domainConvert.modelToDTO(domain));
    }

    @Override
    public DomainDTO updateDomain(String id, Domain domain) {
        Domain domain1 = domainReponsitory.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Not found any domain"));
        domain1.setName(domain.getName());
        final Domain updateDomain = domainReponsitory.save(domain1);
        //return ResponseEntity.ok().body(updateDomain);
        return domainConvert.modelToDTO(updateDomain);
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
