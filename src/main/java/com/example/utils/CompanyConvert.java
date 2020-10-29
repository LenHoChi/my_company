package com.example.utils;

import com.example.dto.CompanyDTO;
import com.example.model.Company;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class CompanyConvert {
    ModelMapper modelMapper =new ModelMapper();
    public Company dtoToModel(CompanyDTO companyDTO){
        Company company=new Company();
        company.setName(companyDTO.getName());
        company.setId(companyDTO.getId());
        company.setPhone(companyDTO.getPhone());
        company.setUrl(companyDTO.getUrl());
        return company;
    }
    public CompanyDTO modelToDTO(Company company){
        CompanyDTO companyDTO=modelMapper.map(company,CompanyDTO.class);
        return companyDTO;
    }
    public List<CompanyDTO> listModelToListDTO(List<Company> companys){
        List<CompanyDTO> companyDTOs=new ArrayList<>();
        for(int i=0;i<companys.size();i++){
            CompanyDTO companyDTO=modelMapper.map(companys.get(i),CompanyDTO.class);
            companyDTOs.add(companyDTO);
        }
        return companyDTOs;
    }
    public Page<CompanyDTO> pageModelToPageDTO(Page<Company> pageModel){
        return pageModel.map(this::modelToDTO);
    }
}
