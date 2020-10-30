package com.example.utils.convert;

import com.example.dto.CompanyDTO;
import com.example.dto.DepartmentDTO;
import com.example.dto.DomainDTO;
import com.example.model.Company;
import com.example.model.Department;
import com.example.model.Domain;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class DomainConvert {
    static ModelMapper modelMapper =new ModelMapper();
    public static Domain dtoToModel(DomainDTO domainDTO){
        Domain domain=new Domain();
        domain.setName(domainDTO.getName());
        domain.setId(domainDTO.getId());
        return domain;
    }
    public static DomainDTO modelToDTO(Domain domain){
        DomainDTO domainDTO=modelMapper.map(domain ,DomainDTO.class);
        return domainDTO;
    }
    public static List<DomainDTO> listModelToListDTO(List<Domain> domains){
        List<DomainDTO> domainDTOS=new ArrayList<>();
        for(int i=0;i<domains.size();i++){
            DomainDTO domainDTO=modelMapper.map(domainDTOS.get(i),DomainDTO.class);
            domainDTOS.add(domainDTO);
        }
        return domainDTOS;
    }
    public static Page<DomainDTO> pageModelToPageDTO(Page<Domain> pageModel){
        return pageModel.map(DomainConvert::modelToDTO);
    }
}
