/**
 * 
 */
package fr.epita.iamcoreTDas.exception;


/**
 * @author ZAC
 *This is the DAO update exception
 */
public class DAOUpdateException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String updateFault;
	
	/**
	 * @param message
	 */
	public DAOUpdateException(String message) {
		this.updateFault = message;
	}

	/**
	 * @return
	 */
	public String getUpdateFault() {
		return updateFault;
	}

}
