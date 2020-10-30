package com.example.utils.convert;

import com.example.dto.CompanyDTO;
import com.example.model.Company;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompanyConvert {
    static ModelMapper modelMapper =new ModelMapper();
    public static Company dtoToModel(CompanyDTO companyDTO){
        Company company=new Company();
        company.setName(String.valueOf(companyDTO.getName()));
        company.setId(String.valueOf(companyDTO.getId()));
        company.setPhone(String.valueOf(companyDTO.getPhone()));
        company.setUrl(String.valueOf(companyDTO.getUrl()));
        return company;
    }
    public static CompanyDTO modelToDTO(Company company){
        CompanyDTO companyDTO=modelMapper.map(company,CompanyDTO.class);
        return companyDTO;
    }
    public static List<CompanyDTO> listModelToListDTO(List<Company> companys){
        List<CompanyDTO> companyDTOs=new ArrayList<>();
        for(int i=0;i<companys.size();i++){
            CompanyDTO companyDTO=modelMapper.map(companys.get(i),CompanyDTO.class);
            companyDTOs.add(companyDTO);
        }
        return companyDTOs;
    }
    public static Page<CompanyDTO> pageModelToPageDTO(Page<Company> pageModel){
        return pageModel.map(CompanyConvert::modelToDTO);
    }

}
