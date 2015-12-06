package ua.alex.source.webtester.service;


import ua.alex.source.webtester.entities.Account;
import ua.alex.source.webtester.entities.AccountRegistration;

import javax.servlet.http.HttpServletRequest;

public interface EmailService {

	void sendVerificationEmail(AccountRegistration user);

	void sendGeneratedPasswordToEmail();
}
