package de.tosken.dockerui.persistance.model;

import com.google.common.base.Objects;

import javax.persistence.*;

/**
 * dockerui
 * User: Sebastian
 * Date: 25.05.2018
 * Time: 20:22
 */
@Entity
@Table(name = "together_item")
public class TogetherItem {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column
    private String title;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="together_id")
    private Together together;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private User creator;

    @ManyToOne
    @JoinColumn(name = "responsible_id")
    private User responsible;

    public String getTitle() {
        return title;
    }

    public Together getTogether() {
        return together;
    }

    public void setTogether(Together together) {
        this.together = together;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TogetherItem that = (TogetherItem) o;
        return id != 0 && id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, getTitle());
    }
}
