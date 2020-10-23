package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "domain")
public class Domain {
    private String id;
    private String name;

    public Domain() {

    }
    public Domain(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @Column(name = "domain_id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Column(name = "domain_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
