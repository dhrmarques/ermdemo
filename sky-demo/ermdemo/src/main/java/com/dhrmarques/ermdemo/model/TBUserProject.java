package com.dhrmarques.ermdemo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_user_external_project")
public class TBUserProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private TBUser user;

    private String name;

    // Default constructor for Jackson deserialization
    public TBUserProject() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TBUser getUser() {
        return user;
    }

    public void setUser(TBUser user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TBUserProject{" + "id=" + id + ", user=" + user + ", name='" + name + '\'' + '}';
    }
}
