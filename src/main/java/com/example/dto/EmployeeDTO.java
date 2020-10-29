package com.example.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.sql.Date;

@Data
public class EmployeeDTO {
    @NotEmpty(message = "id not empty")
    private String id;
    private String name;
    private String gender;
    @Pattern(regexp = "((?:0[1-9])|(?:1[0-2]))\\/((?:0[0-9])|(?:[1-2][0-9])|(?:3[0-1]))\\/(\\d{4})",
            message = "invalid birthday")
    private Date birthday;
    @Pattern(regexp = "(\\+61|0)[0-9]{9}", message = "invalid phone number !!")
    private String phone;
}
