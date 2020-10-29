package com.example.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ProjectDTO {
    @NotEmpty(message = "id not empty")
    private String id;
    private String name;
    private String text;
    private String description;
}
