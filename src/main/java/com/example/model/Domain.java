package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Table(name = "domain")
public class Domain  {
    @Id
    @Column(name = "domain_id")
    private String id;
    @Column(name = "domain_name")
    private String name;

    @OneToMany(mappedBy = "domain", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("domain")
    private Set<Company> companies;
}
