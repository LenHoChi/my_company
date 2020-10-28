package com.example.utils;

import com.example.dto.DepartmentDTO;
import com.example.dto.ProjectDTO;
import com.example.model.Department;
import com.example.model.Project;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ProjectConvert {
    ModelMapper modelMapper =new ModelMapper();
    public ProjectDTO modelToDTo(Project project){
        ProjectDTO projectDTO=modelMapper.map(project,ProjectDTO.class);
        return projectDTO;
    }
    public List<ProjectDTO> listModelToListDTO(List<Project> projects){
        List<ProjectDTO> projectDTOS=new ArrayList<>();
        for(int i=0;i<projects.size();i++){
            ProjectDTO projectDTO=modelMapper.map(projects.get(i),ProjectDTO.class);
            projectDTOS.add(projectDTO);
        }
        return projectDTOS;
    }
}
