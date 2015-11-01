package ua.alex.source.webtester.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "account")
public class Account extends ManagerEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ACCOUNT_IDACCOUNT_GENERATOR", sequenceName = "account_seq")
    @GeneratedValue(generator = "ACCOUNT_IDACCOUNT_GENERATOR", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_account", nullable = false, unique = true)
    private Long idAccount;

    @Column(name = "login", nullable = false, unique = true, length = 60)
    private String login;

    @Column(name = "email", nullable = false, unique = true, length = 60)
    private String email;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "fio", nullable = false, length = 200)
    private String fio;

    @Column(name = "confirm", columnDefinition = "BOOLEAN NOT NULL DEFAULT FALSE")
    private boolean confirm;

    @OneToMany(mappedBy = "account")
    private List<AccountRole> accountRoles;

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
    }

    @Override
    @Transient
    public Serializable getId() {
        return getIdAccount();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }


    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

}
