package fr.epita.iamcoreTDas.service.authentication;

public class AuthenticationService {
	
	public boolean authenticate(String username, String password) {
		//authentication method
		return (username .equals("admin") && password .equals("admin"));
		
	}

}
