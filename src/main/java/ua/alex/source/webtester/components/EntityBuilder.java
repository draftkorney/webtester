package ua.alex.source.webtester.components;


import ua.alex.source.webtester.entities.*;

public interface EntityBuilder {

	Account buildAccount();
	
	AccountRole buildAccountRole(Account account, Role role);

	AccountRegistration buildAccountRegistration(Account account);
	Test buildTest();
}
