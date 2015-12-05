package ua.alex.source.webtester.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.alex.source.webtester.ApplicationConstants;
import ua.alex.source.webtester.dao.AccountDao;
import ua.alex.source.webtester.entities.Account;
import ua.alex.source.webtester.entities.AccountRole;


@Service("accountAuthentificationService")
public class AuthentificationService implements UserDetailsService {
    private static final Map<Integer, String> ROLES = new HashMap<Integer, String>();

    static {
        ROLES.put(ApplicationConstants.ADMIN_ROLE, "ADMIN");
        ROLES.put(ApplicationConstants.TUTOR_ROLE, "TUTOR");
        ROLES.put(ApplicationConstants.ADVANCED_TUTOR_ROLE, "ADVANCED_TUTOR");
        ROLES.put(ApplicationConstants.STUDENT_ROLE, "STUDENT");
    }

    @Autowired
    private AccountDao accountDao;

    static Collection<? extends GrantedAuthority> convert(List<AccountRole> roles) {
        Collection<SimpleGrantedAuthority> res = new ArrayList<SimpleGrantedAuthority>();
        for (AccountRole ar : roles) {
            res.add(new SimpleGrantedAuthority(ROLES.get(ar.getRole().getIdRole())));
        }
        return res;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account a = accountDao.getByLogin(username);
        if (a == null) {
            throw new UsernameNotFoundException("Account not found by id=" + username);
        }
        return new CurrentAccount(a);
    }
}
