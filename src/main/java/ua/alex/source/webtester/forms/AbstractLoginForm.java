package ua.alex.source.webtester.forms;


import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import ua.alex.source.webtester.validations.Unique;

public class AbstractLoginForm implements IForm {
    private static final long serialVersionUID = -8342766271396665602L;

    @NotNull(errorCode = "form.login.null")
    @NotEmpty(errorCode = "form.login.null")
    @Length(min = 5, max = 60, errorCode = "form.login.invalid.size")
    @Unique(errorCode = "form.login.is.exist")
    private String login;

    @NotNull(errorCode = "form.password.null")
    @NotEmpty(errorCode = "form.password.null")
    @Length(min = 6, max = 60, errorCode = "form.password.invalid.size")
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
