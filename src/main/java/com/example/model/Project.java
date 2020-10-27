package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Table(name = "project")
public class Project  {
    @Id
    @Column(name = "project_id")
    private String id;
    @Column(name = "project_name")
    private String name;
    @Column(name = "text")
    private String text;
    @Column(name = "description")
    private String description;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, mappedBy = "projects")
    @JsonIgnoreProperties("projects")
    @EqualsAndHashCode.Exclude private Set<Employee> employees;
}
