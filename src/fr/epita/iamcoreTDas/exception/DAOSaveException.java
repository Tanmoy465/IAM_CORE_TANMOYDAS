/**
 * 
 */
package fr.epita.iamcoreTDas.exception;

/**
 * @author ZAC
 *
 */
public class DAOSaveException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String saveFault;
	
	public DAOSaveException(String message) {
		this.saveFault = message;
	}

	public String getSaveFault() {
		return saveFault;
	}


}
