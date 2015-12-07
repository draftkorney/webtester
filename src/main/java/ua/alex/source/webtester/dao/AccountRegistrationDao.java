package ua.alex.source.webtester.dao;


import org.springframework.stereotype.Service;
import ua.alex.source.webtester.entities.Account;
import ua.alex.source.webtester.entities.AccountRegistration;

import java.util.List;


public interface AccountRegistrationDao extends IEntityDao<AccountRegistration> {

    AccountRegistration getAccountRegistrationByHash(String hashCode);

}
