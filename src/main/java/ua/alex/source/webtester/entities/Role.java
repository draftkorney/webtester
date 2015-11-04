package ua.alex.source.webtester.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Role extends ManagerEntity {

    @Id
    @SequenceGenerator(name = "ROLE_IDROLE_GENERATOR", sequenceName = "role_seq",allocationSize = 1)
    @GeneratedValue(generator = "ROLE_IDROLE_GENERATOR", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_role")
    private short idRole;

    @Column(name = "name", nullable = false, length = 15)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getIdRole() {
        return idRole;
    }

    public void setIdRole(short idRole) {
        this.idRole = idRole;
    }

    @Override
    @Transient
    public Serializable getId() {
        return getIdRole();
    }
}
