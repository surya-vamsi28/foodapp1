package com.learning.service;

import com.learning.dto.Login;
import com.learning.dto.EROLE;

public interface LoginService {
	public String addCredentials(Login login);

	public String deleteCredentials(String userName);

	public String changePassword(String userName,String password);
	
	public String changeRole(String userName, EROLE role);

	
	

}
