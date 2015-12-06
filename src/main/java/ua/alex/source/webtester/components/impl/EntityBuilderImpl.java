package ua.alex.source.webtester.components.impl;

import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.stereotype.Component;
import ua.alex.source.webtester.components.EntityBuilder;
import ua.alex.source.webtester.entities.Account;
import ua.alex.source.webtester.entities.AccountRegistration;
import ua.alex.source.webtester.entities.AccountRole;
import ua.alex.source.webtester.entities.Role;

/**
 * @author nedis
 * @version 1.0
 */
@Component("entityBuilder")
public class EntityBuilderImpl implements EntityBuilder {

    @Override
    public Account buildAccount() {
        Account a = new Account();
        a.setCreated(new Timestamp(System.currentTimeMillis()));
        a.setActive(Boolean.FALSE);
        return a;
    }

    @Override
    public AccountRole buildAccountRole(Account account, Role role) {
        return new AccountRole(account, role);
    }

    @Override
    public AccountRegistration buildAccountRegistration(Account account) {
        return new AccountRegistration(account, UUID.randomUUID().toString());
    }
}
