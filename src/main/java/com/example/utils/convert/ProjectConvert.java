package com.example.utils.convert;

import com.example.dto.CompanyDTO;
import com.example.dto.DepartmentDTO;
import com.example.dto.ProjectDTO;
import com.example.model.Company;
import com.example.model.Department;
import com.example.model.Project;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class ProjectConvert {
    static ModelMapper modelMapper =new ModelMapper();
    public static Project dtoToModel(ProjectDTO projectDTO){
        Project project=new Project();
        project.setName(projectDTO.getName());
        project.setId(projectDTO.getId());
        project.setText(projectDTO.getText());
        project.setDescription(projectDTO.getDescription());
        return project;
    }
    public static ProjectDTO modelToDTO(Project project){
        ProjectDTO projectDTO=modelMapper.map(project,ProjectDTO.class);
        return projectDTO;
    }
    public static List<ProjectDTO> listModelToListDTO(List<Project> projects){
        List<ProjectDTO> projectDTOS=new ArrayList<>();
        for(int i=0;i<projects.size();i++){
            ProjectDTO projectDTO=modelMapper.map(projects.get(i),ProjectDTO.class);
            projectDTOS.add(projectDTO);
        }
        return projectDTOS;
    }
    public static Page<ProjectDTO> pageModelToPageDTO(Page<Project> pageModel){
        return pageModel.map(ProjectConvert::modelToDTO);
    }
}
