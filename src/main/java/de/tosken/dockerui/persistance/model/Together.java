package de.tosken.dockerui.persistance.model;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * dockerui
 * User: Sebastian
 * Date: 25.05.2018
 * Time: 20:22
 */
@Entity
@Table(name = "together")
public class Together {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(nullable = false)
    private String ref;

    @Column
    private String title;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date expires;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "together")
    private Set<TogetherItem> items;

    @Column
    private boolean extendable;

    public void addItem(final TogetherItem item) {
        if (items == null)
            items = new HashSet<>();
        items.add(item);
        item.setTogether(this);
    }

    public void removeItem(final TogetherItem item) {
        if (items != null) {
            items.remove(item);
            item.setTogether(null);
        }
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Set<TogetherItem> getItems() {
        return items;
    }

    public void setItems(Set<TogetherItem> items) {
        this.items = items;
    }

    public boolean isExtendable() {
        return extendable;
    }

    public void setExtendable(boolean extendable) {
        this.extendable = extendable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Together together = (Together) o;
        return Objects.equal(ref, together.ref);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ref);
    }
}
