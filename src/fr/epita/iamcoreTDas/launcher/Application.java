/**
 * 
 */
package fr.epita.iamcoreTDas.launcher;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.*;

import fr.epita.iamcoreTDas.datamodel.Identity;
import fr.epita.iamcoreTDas.exception.DAODeleteException;
import fr.epita.iamcoreTDas.exception.DAOInitializationException;
import fr.epita.iamcoreTDas.exception.DAOSaveException;
import fr.epita.iamcoreTDas.exception.DAOUpdateException;
import fr.epita.iamcoreTDas.service.authentication.AuthenticationService;
import fr.epita.iamcoreTDas.service.dao.IdentityJDBCDAO;


/**
 * @author ZAC
 *
 */
public class Application {
	
	private static final Logger logger =  LogManager.getLogger(Application.class);

	/**
	 * @param args
	 * @throws IOException
	 * @throws SQLException 
	 */

	
	public static void main(String[] args) throws IOException, SQLException {
		
		logger.info("program started");
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the IAM System");

		System.out.println("Please enter your user name: ");
		String username = scanner.nextLine();
		System.out.println("Please enter your password: ");
		String password = scanner.nextLine();
		
		logger.info("program received this input : user = {}, password ={} ", username, 
				(password == null ) ? "null" : "****");
		/**
		 * The Credentials are compared against the preset Credentials
		 */
		
		AuthenticationService authService = new AuthenticationService();
		if (!authService.authenticate(username, password)) {
			System.out.println("The provided credentials are wrong");
			System.out.println("The program will now exit\n");
			scanner.close();
			return;
		}
		IdentityJDBCDAO dao;
		
		try {
			
			dao = IdentityJDBCDAO.getInstance();
		} catch (DAOInitializationException e) {
			System.out.println(e.getInitializationFault());
			System.out.println("unable to initialize, exiting");
			scanner.close();
			return;
		}
		
		/**
		 * 4 operations are presented to the user
		 * A while loop is created to repeat the operations until the exit operation is selected
		 */
		boolean repeat = true;
		while (repeat)
		{
		Identity identity;
		String displayName, email, uid;
		System.out.println("Menu for the IAM application :");
		System.out.println("1 - Create an Identity");
		System.out.println("2 - Update an Identity");
		System.out.println("3 - Delete an Identity");
		System.out.println("4 - Display an Identity");
		System.out.println("5 - Exit");
		System.out.print("your choice (1|2|3|4|5) : "); 
		String menuAnswer = scanner.nextLine();
		
		switch (menuAnswer) {
		/*
		 * case 1 - operation 1; create an Identity
		 */
		case "1":
			System.out.println("Creation Activity");

			System.out.print("Please enter the Identity display name");
			displayName = scanner.nextLine();
			
			System.out.print("Please enter the Identity email address");
			email = scanner.nextLine();
			
			
			identity = new Identity(displayName, email, null);
			
			System.out.println("Identity created");
			System.out.println("Identity display name = "+displayName);
			System.out.println("Identity email address = "+email +"\n");
			

			try {
				dao.save(identity);
			} catch (DAOSaveException e) {
				System.out.println(e.getSaveFault());
			
			}
				
			break;
			/*
			 * Case 2 - operation 2; modify an Identity
			 */
		case "2":
			 
			System.out.println("Modification Activity");
			System.out.print("Please enter the Identity uid");
			uid = scanner.nextLine();
			System.out.print("Please enter the new Identity display name");
			displayName = scanner.nextLine();
			System.out.print("Please enter the new Identity email address");
			email = scanner.nextLine();
			
			identity = new Identity(displayName, email, uid);
			
			System.out.println("Identity updated");
			System.out.println("New Identity display name = "+displayName);
			System.out.println("New Identity email address = "+email+"\n");

			
			try {
				dao.update(identity);
			} catch (DAOUpdateException e) {
				System.out.println(e.getUpdateFault());
			}

			
			break;
			/*
			 * case 3 - operation 3; Delete an identity
			 */
		case "3":
			 
			System.out.println("Deletion Activity");
			System.out.print("Please enter the Identity uid");
			uid = scanner.nextLine();
			
			
			identity = new Identity(null, null, uid);
			try {
				dao.delete(identity);
			} catch (DAODeleteException e) {
				System.out.println(e.getDeleteFault());
			}
			System.out.println("Identity deleted\n");
			break;
			/*
			 * case 4 - Displays Identities
			 */
		case "4":
			System.out.println("Display Activity");
			System.out.println("This is the list of all identities in the system");
			List<Identity> list = dao.readAll();
			int size = list.size();
			for(int i = 0; i < size; i++){
				System.out.println( i+ "." + list.get(i));
			}
			
			break;
			
			/*
			 * case 5 - Exit; exits the while loop to exit the application
			 */
		case "5":
			System.out.println("The program will now exit\n");
			scanner.close();
			return;

		default:
			System.out.println("Invalid Entry");
			break;
		}
		if (menuAnswer=="5")
			repeat=false;
		}
		scanner.close();
	}
	
}


