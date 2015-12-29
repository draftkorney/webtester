package ua.alex.source.webtester.forms;


import net.sf.oval.constraint.*;
import org.apache.commons.lang.StringUtils;
import ua.alex.source.webtester.validations.CheckPassword;
import ua.alex.source.webtester.validations.Email;
import ua.alex.source.webtester.validations.Unique;

import java.io.Serializable;

public class EditAccountForm implements IUnique, IForm {
    private static final long serialVersionUID = -3633827335080843887L;

    Long idAccount;

    @CheckPassword(message = "form.old.password.incorrect")
    private String oldP;

    @ValidateWithMethod(message = "form.password.null", methodName = "checkNewPass", parameterType = String.class)
    private String newP;

    @ValidateWithMethod(message = "form.signup.confirm.pass.not.equal", methodName = "checkConfirmPass", parameterType = String.class)
    private String confirmNP;

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
        return getIdAccount();
    }

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
    }

    public String getOldP() {
        return oldP;
    }

    public void setOldP(String oldP) {
        this.oldP = oldP;
    }

    public String getNewP() {
        return newP;
    }

    public void setNewP(String newP) {
        this.newP = newP;
    }

    public String getConfirmNP() {
        return confirmNP;
    }

    public void setConfirmNP(String confirmNP) {
        this.confirmNP = confirmNP;
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

    private boolean checkNewPass(String newPass) {
        return StringUtils.isBlank(getOldP()) || StringUtils.isNotBlank(getNewP());

    }

    private boolean checkConfirmPass(String newPass) {
        return getNewP().equals(getConfirmNP());
    }

}
