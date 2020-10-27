package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "employee")
//@EqualsAndHashCode(exclude="department")
public class Employee  {
    @Id
    @Column(name = "employee_id")
    private String id;
    @Column(name = "employee_name")
    private String name;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "gender")
    private String gender;
    @Column(name = "phone")
    private String phone;
    @Column(name = "department_id")
    private String department_id;

    @ManyToOne
    @JoinColumn(name = "department_id", insertable = false, updatable = false)
    @JsonIgnoreProperties("employees")
    @EqualsAndHashCode.Exclude private Department department;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade= CascadeType.ALL)
    @JoinTable(name = "employee_projects",
            joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")})
    @JsonIgnoreProperties("employees")
    @EqualsAndHashCode.Exclude private Set<Project> projects;
}
