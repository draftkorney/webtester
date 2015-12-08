package ua.alex.source.webtester.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "account_role", schema = "public", catalog = "webtester")
public class AccountRole extends AbstractEntity {

    @Id
    @SequenceGenerator(name = "ACCOUNT_ROLE_IDACCOUNT_ROLE_GENERATOR", sequenceName = "account_role_seq",allocationSize = 1)
    @GeneratedValue(generator = "ACCOUNT_ROLE_IDACCOUNT_ROLE_GENERATOR", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_account_role", nullable = false, unique = true)
    private long idAccountRole;

    @ManyToOne
    @JoinColumn(name = "id_account", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "id_role", nullable = false)
    private Role role;

    private Timestamp updated;

    public AccountRole(Account account, Role role) {
        this.account = account;
        this.role = role;
    }

    public AccountRole() {
    }

    public long getIdAccountRole() {
        return idAccountRole;
    }

    public void setIdAccountRole(long idAccountRole) {
        this.idAccountRole = idAccountRole;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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
        return getIdAccountRole();
    }
}
