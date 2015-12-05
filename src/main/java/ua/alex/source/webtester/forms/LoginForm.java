package ua.alex.source.webtester.forms;


import ua.alex.source.webtester.forms.AbstractLoginForm;


public class LoginForm extends AbstractLoginForm {
    private static final long serialVersionUID = -8737364475275749590L;

    private int idRole;

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }
}
