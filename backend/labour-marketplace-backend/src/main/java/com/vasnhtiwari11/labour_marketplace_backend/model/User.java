package com.vasnhtiwari11.labour_marketplace_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name="users")

public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    
    private Long id;

    @Column(nullable=false, unique=true)
    private String phoneNumber;

    @Column(nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;


    public User(){

    }

    public User(String phoneNumber, String fullName, UserRole role){
        this.phoneNumber= phoneNumber;
        this.fullName= fullName;
        this.role= role;
    }

    public Long getId(){
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
