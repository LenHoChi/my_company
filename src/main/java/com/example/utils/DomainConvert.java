package com.example.utils;

import com.example.dto.DepartmentDTO;
import com.example.dto.DomainDTO;
import com.example.model.Department;
import com.example.model.Domain;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class DomainConvert {
    ModelMapper modelMapper =new ModelMapper();
    public DomainDTO modelToDTO(Domain domain){
        DomainDTO domainDTO=modelMapper.map(domain ,DomainDTO.class);
        return domainDTO;
    }
    public List<DomainDTO> listModelToListDTO(List<Domain> domains){
        List<DomainDTO> domainDTOS=new ArrayList<>();
        for(int i=0;i<domains.size();i++){
            DomainDTO domainDTO=modelMapper.map(domainDTOS.get(i),DomainDTO.class);
            domainDTOS.add(domainDTO);
        }
        return domainDTOS;
    }
}
