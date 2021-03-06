package com.example.dto;

import com.example.model.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.example.dto.DepartmentDTO;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
    @NotEmpty(message = "id not empty")
    private String id;
    private String name;
    private String url;
    @Pattern(regexp = "(\\+84|0)[0-9]{9}", message = "invalid phone number !!")
    private String phone;
}
