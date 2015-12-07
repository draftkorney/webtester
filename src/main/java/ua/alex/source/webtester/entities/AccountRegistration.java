package ua.alex.source.webtester.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "account_registration", schema = "public", catalog = "webtester")
public class AccountRegistration extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "gen")
    @GenericGenerator(name = "gen", strategy = "foreign", parameters = @Parameter(name = "property", value = "account"))
    @Column(name = "id_account", unique = true, nullable = false)
    private Long idAccount;

    @Column(name = "hash", nullable = false)
    private String hash;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Account account;

    public AccountRegistration(Account account, String uuid) {
        this.hash = uuid;
        this.account = account;
    }

    public AccountRegistration() {
    }

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idAccount) {
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
