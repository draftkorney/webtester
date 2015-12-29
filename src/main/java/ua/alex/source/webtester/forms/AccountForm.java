package ua.alex.source.webtester.forms;


import net.sf.oval.constraint.*;
import ua.alex.source.webtester.validations.Email;
import ua.alex.source.webtester.validations.Unique;

import java.io.Serializable;
import java.util.List;

public class AccountForm implements IUnique, IForm {

    Long idAccount;

    @NotNull(message = "form.login.null")
    @NotBlank(message = "form.login.null")
    @Length(min = 5, max = 60, message = "form.login.invalid.size")
    @Unique(message = "form.login.is.exist")
    private String login;

    @NotNull(message = "form.email.null")
    @NotBlank(message = "form.email.null")
    @Length(min = 5, max = 60, message = "form.email.invalid.size")
    @Unique(message = "form.email.is.exist")
    @Email(message = "form.email.wrong")
    private String email;

    @NotNull(message = "form.fio.null")
    @NotBlank(message = "form.fio.null")
    @Length(min = 5, max = 200, message = "form.fio.invalid.size")
    private String fio;

    @NotNull(message = "form.roles.null")
    @Size(min = 1, max = 4, message = "form.roles.invalid.size")
    private List<Integer> roles;

    @Override
    public Serializable getId() {
        return this.idAccount;
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
        this.email = email.toLowerCase();
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public List<Integer> getRoles() {
        return roles;
    }

    public void setRoles(List<Integer> roles) {
        this.roles = roles;
    }

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
    }
}
