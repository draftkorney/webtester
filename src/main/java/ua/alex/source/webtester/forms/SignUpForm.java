package ua.alex.source.webtester.forms;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import ua.alex.source.webtester.validations.Unique;

import javax.validation.constraints.Size;

public class SignUpForm extends AbstractLoginForm implements IForm {
    private static final long serialVersionUID = -3633827335080843887L;

    @NotEmpty
    @Size(min = 6, max = 60)
    private String confirmPass;

    @NotEmpty
    @Size(min = 6, max = 60)
    @Email
    @Unique()
    private String email;

    @NotEmpty
    @Size(min = 6, max = 200)
    private String fio;

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }


}
