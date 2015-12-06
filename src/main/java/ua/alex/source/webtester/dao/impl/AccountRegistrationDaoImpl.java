package ua.alex.source.webtester.dao.impl;


import org.springframework.stereotype.Service;
import ua.alex.source.webtester.dao.AccountDao;
import ua.alex.source.webtester.dao.AccountRegistrationDao;
import ua.alex.source.webtester.dao.IEntityDao;
import ua.alex.source.webtester.entities.Account;
import ua.alex.source.webtester.entities.AccountRegistration;

@Service
public class AccountRegistrationDaoImpl extends AbstractEntityDao<AccountRegistration> implements AccountRegistrationDao {

    @Override
    protected Class<AccountRegistration> getEntityClass() {
        return AccountRegistration.class;
    }
}
