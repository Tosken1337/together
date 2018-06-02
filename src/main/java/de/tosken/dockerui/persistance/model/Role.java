package de.tosken.dockerui.persistance.model;

import javax.persistence.*;

/**
 * zul
 * User: Sebastian
 * Date: 24.04.2018
 * Time: 20:08
 */
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "role_name")
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
