package ua.alex.source.webtester.forms;


import net.sf.oval.constraint.*;
import ua.alex.source.webtester.validations.Email;
import ua.alex.source.webtester.validations.Unique;

import java.util.List;

public class AccountForm implements IForm {

    @NotNull(errorCode = "form.login.null")
    @NotBlank(errorCode = "form.login.null")
    @Length(min = 5, max = 60, errorCode = "form.login.invalid.size")
    @Unique(errorCode = "form.login.is.exist")
    private String login;

    @NotNull(errorCode = "form.email.null")
    @NotBlank(errorCode = "form.email.null")
    @Length(min = 5, max = 60, errorCode = "form.email.invalid.size")
    @Unique(errorCode = "form.email.is.exist")
    @Email(errorCode = "form.email.wrong")
    private String email;

    @NotNull(errorCode = "form.fio.null")
    @NotBlank(errorCode = "form.fio.null")
    @Length(min = 5, max = 200, errorCode = "form.fio.invalid.size")
    private String fio;

    @NotNull(errorCode = "form.roles.null")
    @Size(min = 1, max = 4, errorCode = "form.roles.invalid.size")
    private List<Integer> roles;

    Long idAccount;

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
