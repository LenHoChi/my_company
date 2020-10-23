package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department {
    private String id;
    private String name;
    private String description;
    private String email;
    private String company_Id;

    public Department() {

    }
    public Department(String id, String name, String description, String email, String company_Id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.email = email;
        this.company_Id = company_Id;
    }
    @Id
    @Column(name = "department_id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Column(name = "department_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name = "company_id")
    public String getCompany_Id() {
        return company_Id;
    }

    public void setCompany_Id(String company_Id) {
        this.company_Id = company_Id;
    }
}
