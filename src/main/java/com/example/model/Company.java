package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "company", schema = "public")
@Data
//@EqualsAndHashCode(exclude="domain")
public class Company {
    @Id
    @Column(name = "company_id")
    private String id;
    @Column(name = "company_name")
    private String name;
    @Column(name = "url")
    private String url;
    @Column(name = "phone")
    private String phone;
    @Column(name = "domain_id")
    private String domainID;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "company", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("company")
//    @Fetch(value=FetchMode.SELECT)
    private Set<Department> departments;

    @ManyToOne
    @JoinColumn(name = "domain_id", insertable = false, updatable = false)
    @JsonIgnoreProperties("companies")
    @EqualsAndHashCode.Exclude private Domain domain;
}
