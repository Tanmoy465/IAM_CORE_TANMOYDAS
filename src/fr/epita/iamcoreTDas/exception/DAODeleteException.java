/**
 * 
 */
package fr.epita.iamcoreTDas.exception;

/**
 * @author ZAC
 * This is the DAO delete exception
 */
public class DAODeleteException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String deleteFault;
	
	/**
	 * @param message
	 */
	public DAODeleteException(String message) {
		this.deleteFault = message;
	}

	/**
	 * @return
	 */
	public String getDeleteFault() {
		return deleteFault;
	}

}
