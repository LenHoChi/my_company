package com.example.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class DomainDTO {
    @NotEmpty(message = "id not empty")
    private String id;
    private String name;
}
