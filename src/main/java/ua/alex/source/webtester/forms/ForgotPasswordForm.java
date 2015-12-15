package ua.alex.source.webtester.forms;


import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;
import ua.alex.source.webtester.validations.Email;
import ua.alex.source.webtester.validations.MustExist;

public class ForgotPasswordForm implements IForm {

    @NotNull(errorCode = "form.email.null")
    @NotBlank(errorCode = "form.email.null")
    @Email(errorCode = "form.email.wrong")
    @MustExist(errorCode = "form.forgot.email.exist")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

}
