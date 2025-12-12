package com.dhrmarques.ermdemo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_user")
public class TBUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique=true)
    private String email;

    private String password;
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TBUserProject> projects;

    // Default constructor for Jackson deserialization
    public TBUser() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TBUser{" + "id=" + id + ", email='" + email + '\'' + ", password='" + password + '\'' + ", name='" + name + '\'' + '}';
    }
}
