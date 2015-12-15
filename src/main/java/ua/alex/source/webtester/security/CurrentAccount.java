package ua.alex.source.webtester.security;


import org.springframework.security.core.userdetails.User;
import ua.alex.source.webtester.entities.Account;
import ua.alex.source.webtester.utils.CommonUtils;

import java.util.List;


public class CurrentAccount extends User {
    private static final long serialVersionUID = 8252540687900090175L;
    private final long idAccount;
    private List<Integer> roles;
    private boolean active;

    public CurrentAccount(Account a) {
        super(a.getEmail(), a.getPassword(), a.getActive(), true, true, true,
                AuthentificationService.convert(a.getAccountRoles()));
        CommonUtils.convertRoles(a.getAccountRoles());
        this.idAccount = a.getIdAccount();

    }

    public long getIdAccount() {
        return idAccount;
    }

    public List<Integer> getRoles() {
        return roles;
    }

    public void setRoles(List<Integer> roles) {
        this.roles = roles;
    }

}
