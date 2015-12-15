package ua.alex.source.webtester.forms;


import net.sf.oval.constraint.EqualToField;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import ua.alex.source.webtester.validations.Email;
import ua.alex.source.webtester.validations.Unique;

public class SignUpForm extends AbstractLoginForm implements IForm {
    private static final long serialVersionUID = -3633827335080843887L;

    @EqualToField(value = "password",errorCode = "form.signup.confirm.pass.not.equal")
    private String confirmPass;

    @NotNull(errorCode = "form.email.null")
    @NotEmpty(errorCode = "form.email.null")
    @Length(min = 5, max = 60, errorCode = "form.email.invalid.size")
    @Unique(errorCode = "form.email.is.exist")
    @Email(errorCode = "form.email.wrong")
    private String email;

    @NotNull(errorCode = "form.fio.null")
    @NotEmpty(errorCode = "form.fio.null")
    @Length(min = 5, max = 200, errorCode = "form.fio.invalid.size")
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
