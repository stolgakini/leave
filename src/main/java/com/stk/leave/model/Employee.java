package com.stk.leave.model;


import javax.persistence.*;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;


@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "employee_id")
    private List<Leave> leaveList;

    @Column
    private String tckn;

    @Column
    private String name;

    @Column
    private String role;

    @Column
    private String surname;

    @Column
    private String gender;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    @Column
    private String address;

    @Column
    private Calendar startDate;

    @Column
    @Temporal(TemporalType.DATE)
    private Calendar endDate;



    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTckn() {
        return tckn;
    }

    public void setTckn(String tckn) {
        this.tckn = tckn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public List<Leave> getLeaveList() {
        return leaveList;
    }

    public void setLeaveList(List<Leave> leaveList) {
        this.leaveList = leaveList;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", tckn=" + tckn +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", leaveList=" + leaveList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(getId(), employee.getId()) &&
                Objects.equals(getTckn(), employee.getTckn()) &&
                Objects.equals(getName(), employee.getName()) &&
                Objects.equals(getRole(), employee.getRole()) &&
                Objects.equals(getSurname(), employee.getSurname()) &&
                Objects.equals(getGender(), employee.getGender()) &&
                Objects.equals(getEmail(), employee.getEmail()) &&
                Objects.equals(getPhoneNumber(), employee.getPhoneNumber()) &&
                Objects.equals(getAddress(), employee.getAddress()) &&
                Objects.equals(getStartDate(), employee.getStartDate()) &&
                Objects.equals(getEndDate(), employee.getEndDate()) &&
                Objects.equals(getLeaveList(), employee.getLeaveList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTckn(), getName(), getRole(), getSurname(), getGender(), getEmail(), getPhoneNumber(), getAddress(), getStartDate(), getEndDate(), getLeaveList());
    }
}
