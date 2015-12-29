package ua.alex.source.webtester.forms;


import net.sf.oval.constraint.EqualToField;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import ua.alex.source.webtester.validations.Email;
import ua.alex.source.webtester.validations.Unique;

import java.io.Serializable;

public class SignUpForm extends AbstractLoginForm implements IUnique, IForm {
    private static final long serialVersionUID = -3633827335080843887L;

    @EqualToField(value = "password", message = "form.signup.confirm.pass.not.equal")
    private String confirmPass;

    @NotNull(message = "form.email.null")
    @NotEmpty(message = "form.email.null")
    @Length(min = 5, max = 60, message = "form.email.invalid.size")
    @Unique(message = "form.email.is.exist")
    @Email(message = "form.email.wrong")
    private String email;

    @NotNull(message = "form.fio.null")
    @NotEmpty(message = "form.fio.null")
    @Length(min = 5, max = 200, message = "form.fio.invalid.size")
    private String fio;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public Serializable getId() {
        return null;
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
