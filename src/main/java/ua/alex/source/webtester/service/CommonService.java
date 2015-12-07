package ua.alex.source.webtester.service;

import java.util.List;


import com.restfb.types.User;
import ua.alex.source.webtester.entities.Account;
import ua.alex.source.webtester.entities.Role;
import ua.alex.source.webtester.exceptions.InvalidUserInputException;
import ua.alex.source.webtester.forms.SignUpForm;

public interface CommonService {

	Account login (String email, String password, int role) throws InvalidUserInputException;
	
	Account signUp (SignUpForm form) throws InvalidUserInputException;
	
	Account login (User user) throws InvalidUserInputException;

	void activateAccount(String hashCode);
	
	List<Role> listAllRoles();
}
