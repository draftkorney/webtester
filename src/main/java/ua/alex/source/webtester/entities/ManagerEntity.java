package ua.alex.source.webtester.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
public abstract class ManagerEntity extends AbstractEntity {

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private Timestamp created;

    private Timestamp updated;

    @Override
    @Transient
    public abstract Serializable getId();

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }
}
