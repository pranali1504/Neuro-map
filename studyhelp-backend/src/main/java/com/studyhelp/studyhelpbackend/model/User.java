package com.studyhelp.studyhelpbackend.model;

import jakarta.persistence.*;

@Entity             //Tells Spring: “This class should be stored in DB”
@Table(name = "users")               //Table name will be users
public class User {

    @Id         //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)            //Auto-increment ID
    private Long id;

    @Column(nullable = false, unique = true)              //No two users can have same email --unique=true
    private String email;

    @Column(nullable = false)
    private String password;

    // Constructors
    public User() {}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
