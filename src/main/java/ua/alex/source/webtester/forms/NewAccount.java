package ua.alex.source.webtester.forms;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import ua.alex.source.webtester.validations.Unique;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class NewAccount implements IForm {

    @NotEmpty
    @Size(min = 6, max = 60)
    @Unique(fieldName = "login")
    private String login;

    @NotEmpty
    @Size(min = 6, max = 60)
    @Unique(fieldName = "email")
    @Email
    private String email;

    @NotEmpty
    @Size(min = 6, max = 200)
    private String fio;

    @NotNull
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
        this.email = email;
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
}
