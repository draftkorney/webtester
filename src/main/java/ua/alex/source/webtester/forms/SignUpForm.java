package ua.alex.source.webtester.forms;


import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignUpForm extends AbstractLoginForm implements IForm {
    private static final long serialVersionUID = -3633827335080843887L;

    @NotNull()
    @Size(min = 6, max = 60)
    private String confirmPass;

    @NotNull
    @Size(min = 6, max = 60)
    @Email
    private String email;

    @NotNull
    @Size(min = 6, max = 200)
    private String fio;

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }


}
