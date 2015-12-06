package ua.alex.source.webtester.forms;


import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AbstractLoginForm implements IForm {
    private static final long serialVersionUID = -8342766271396665602L;

    @NotEmpty(message = "{form.login.null}")
    @Size(min = 1, max = 60, message = "form.login.invalid.size")
    private String login;

    @NotEmpty(message = "form.password.null")
    @Size(min = 6, max = 60, message = "form.password.invalid.size")
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
