package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "department", schema = "public")
@Data
@AllArgsConstructor

@NoArgsConstructor
//@EqualsAndHashCode(exclude="company")
//@EqualsAndHashCode(of = "abc")
public class Department  {
    @Id
    @Column(name = "department_id")
    private String id;
    @Column(name = "department_name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "email")
    private String email;
    @Column(name = "company_id")
    private String companyId;
    @ManyToOne
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    @JsonIgnoreProperties("departments")
    @EqualsAndHashCode.Exclude private Company company;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("department")
    private Set<Employee> employees;
}
