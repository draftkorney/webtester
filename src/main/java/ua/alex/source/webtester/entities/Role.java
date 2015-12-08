package ua.alex.source.webtester.entities;

import org.omg.PortableInterceptor.INACTIVE;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class Role extends AbstractEntity {

    @Id
    @SequenceGenerator(name = "ROLE_IDROLE_GENERATOR", sequenceName = "role_seq",allocationSize = 1)
    @GeneratedValue(generator = "ROLE_IDROLE_GENERATOR", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_role")
    private Integer idRole;

    @Column(name = "name", nullable = false, length = 15)
    private String name;

    private Timestamp updated;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    @Override
    @Transient
    public Serializable getId() {
        return getIdRole();
    }
}
