package ua.alex.source.webtester.service;


import ua.alex.source.webtester.entities.Account;
import ua.alex.source.webtester.entities.AccountRegistration;

public interface EmailService {

	void sendVerificationEmail(AccountRegistration user);

	void confirmNewUser(Account account);

	void sendGeneratedPasswordToEmail();

	void sendNewEmailOrLogin(Account account, boolean isNewLogin, boolean isNewEmail);

	void sendForgotPassword(Account account);
}
