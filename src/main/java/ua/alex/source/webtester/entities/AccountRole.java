package ua.alex.source.webtester.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "account_role", schema = "public", catalog = "webtester")
public class AccountRole extends ManagerEntity {

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

    @Override
    @Transient
    public Serializable getId() {
        return getIdAccountRole();
    }
}
