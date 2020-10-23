package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "employee")
public class Employee {
    private String id;
    private String name;
    private Date birthday;
    private String gender;
    private String phone;
    private String department_id;

    public Employee() {

    }
    public Employee(String id, String name, Date birthday, String gender, String phone, String department_id) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.department_id = department_id;
    }
    @Id
    @Column(name = "employee_id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Column(name = "employee_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Column(name = "department_id")
    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }
}
