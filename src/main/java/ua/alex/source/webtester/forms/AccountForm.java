package ua.alex.source.webtester.forms;


import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Size;
import org.hibernate.validator.constraints.Email;
import ua.alex.source.webtester.validations.Unique;

import java.util.List;

public class AccountForm implements IForm {

    Long idAccount;

    @NotEmpty
    @Length(min = 5, max = 60, errorCode = "form.login.invalid.size")
    @Unique()
    private String login;

    @NotEmpty
    @Length(min = 5, max = 60)
    @Unique()
    @Email
    private String email;

    @NotEmpty
    @Length(min = 5, max = 200)
    private String fio;

    @NotNull
    @Size(min = 1, max = 4)
    private List<Integer> roles;

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
