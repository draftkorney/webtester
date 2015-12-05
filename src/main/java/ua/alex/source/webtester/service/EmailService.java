package ua.alex.source.webtester.service;


public interface EmailService {

	void sendVerificationEmail();
	
	void sendGeneratedPasswordToEmail();
}
