package ua.alex.source.webtester.forms;


import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;
import ua.alex.source.webtester.validations.Email;
import ua.alex.source.webtester.validations.MustExist;

public class ForgotPasswordForm implements IForm {

    @NotNull(message = "form.email.null")
    @NotBlank(message = "form.email.null")
    @Email(message = "form.email.wrong")
    @MustExist(message = "form.forgot.email.exist")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

}
