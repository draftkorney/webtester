package ua.alex.source.webtester.security;


import org.springframework.security.core.userdetails.User;
import ua.alex.source.webtester.entities.Account;
import ua.alex.source.webtester.entities.AccountRole;

import java.util.ArrayList;
import java.util.List;


public class CurrentAccount extends User {
    private static final long serialVersionUID = 8252540687900090175L;
    private final long idAccount;
    private List<Integer> roles;

    public CurrentAccount(Account a) {
        super(a.getEmail(), a.getPassword(), a.getActive(), true, true, true,
                AuthentificationService.convert(a.getAccountRoles()));
        convertRoles(a.getAccountRoles());
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

    private void convertRoles(List<AccountRole> roles) {
        List<Integer> res = new ArrayList<>();
        for (AccountRole ar : roles) {
            res.add(ar.getRole().getIdRole());
        }
        this.roles = res;
    }
}
