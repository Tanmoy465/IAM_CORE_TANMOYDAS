/**
 * 
 */
package fr.epita.iamcoreTDas.exception;


/**
 * @author ZAC
 * This is the DAO initialization exception
 */
public class DAOInitializationException extends Exception {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String initializationFault;
	
	private DAOExceptionsMessages message;
	
	/**
	 * @param message
	 */
	public DAOInitializationException(String message) {
		this.initializationFault =  message;
	}
	
	/**
	 * @param message
	 */
	public DAOInitializationException(DAOExceptionsMessages message) {
		this.message =  message;
	}

	/**
	 * @return
	 */
	public String getInitializationFault() {
		return initializationFault;
	}

	
	
}
