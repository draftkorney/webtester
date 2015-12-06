package ua.alex.source.webtester.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "account_registration", schema = "public", catalog = "webtester")
public class AccountRegistration extends AbstractEntity {

    @Id
    @Column(name = "id_account", unique = true, nullable = false)
    private int idAccount;

    @Column(name = "hash", nullable = false)
    private String hash;

    @OneToOne
    @JoinColumn(name = "id_account", nullable = false, updatable = false, insertable = false)
    private Account account;

    public AccountRegistration(Account account, String uuid) {
        this.hash = uuid;
        this.account = account;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    @Transient
    public Serializable getId() {
        return getIdAccount();
    }
}
