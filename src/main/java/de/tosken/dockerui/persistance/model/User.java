package de.tosken.dockerui.persistance.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * zul
 * User: Sebastian
 * Date: 24.04.2018
 * Time: 20:08
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String eMail;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_has_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> roles = new HashSet<>();


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "creator")
    private Set<Together> togethers;

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String geteMail() {
        return eMail;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Set<Together> getTogethers() {
        return togethers;
    }

    public void setTogethers(Set<Together> togethers) {
        this.togethers = togethers;
    }
}
