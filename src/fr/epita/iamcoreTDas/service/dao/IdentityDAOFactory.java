/**
 * 
 */
package fr.epita.iamcoreTDas.service.dao;

import fr.epita.iamcoreTDas.exception.DAOInitializationException;
import fr.epita.iamcoretTDas.service.dao.IdentityDAOInterface;

/**
 * @author ZAC
 *
 */
public class IdentityDAOFactory {
	
	
	public static IdentityDAOInterface getIdentityDAO() throws DAOInitializationException{
		return IdentityJDBCDAO.getInstance();
	}

}
