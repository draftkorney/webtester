package ua.alex.source.webtester.components;


import ua.alex.source.webtester.entities.Account;
import ua.alex.source.webtester.entities.AccountRole;
import ua.alex.source.webtester.entities.Role;

public interface EntityBuilder {

	Account buildAccount();
	
	AccountRole buildAccountRole(Account account, Role role);
}
