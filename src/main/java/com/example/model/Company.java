package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "company", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
//@EqualsAndHashCode(exclude="domain")
public class Company implements Serializable {
    @Id
    @Column(name = "company_id")
    @NonNull
    private String id;
    @Column(name = "company_name")
    @NonNull
    private  String name;
    @Column(name = "url")
    @NonNull
    private String url;
    @Column(name = "phone")
    @NonNull
    private String phone;
    @Column(name = "domain_id")
    @NonNull
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
