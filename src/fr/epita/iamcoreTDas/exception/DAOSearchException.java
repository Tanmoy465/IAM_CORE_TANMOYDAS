/**
 * 
 */
package fr.epita.iamcoreTDas.exception;


/**
 * @author ZAC
 * This is the DAO search exception
 */
public class DAOSearchException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String searchFault;
	
	/**
	 * @param message
	 */
	public DAOSearchException(String message) {
		this.searchFault = message;
	}

	/**
	 * @return
	 */
	public String getSearchFault() {
		return searchFault;
	}

}
